package co.example.ui.search;

import android.content.Intent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.disposables.Disposable;
import co.example.core.BasePresenterImpl;
import co.example.model.search.Category;
import co.example.model.search.Region;
import co.example.network.ApiManager;
import co.example.utils.PreferencesHelper;

import static co.example.ui.companyList.Constants.CATEGORY_ID;
import static co.example.ui.companyList.Constants.DISTRICT_ID;
import static co.example.ui.companyList.Constants.REGION_ID;
import static co.example.ui.companyList.Constants.SEARCH_TEXT;
import static co.example.ui.companyList.Constants.TITLE;

/**
 * Created by Crish on 27.12.2017.
 */

public class SearchPresenterImpl extends BasePresenterImpl<SearchView> implements SearchPresenter {
    private ApiManager apiManager;
    private List<Category> categories;
    private List<List<Category>> subCategory;
    private List<Region> regions;
    private List<List<Region>> districts;
    private String all;
    private Disposable disposable;
    private PreferencesHelper preferencesHelper;

    @Inject
    public SearchPresenterImpl(SearchView view, ApiManager apiManager, PreferencesHelper preferencesHelper, @Named(value = "all") String all) {
        super(view);
        this.apiManager = apiManager;
        this.all = all;
        this.preferencesHelper = preferencesHelper;
    }

    @Override
    public void getSearchData() {
        view.showProgressBar();

        disposable = apiManager.getSearch(preferencesHelper.getLanguage()).subscribe(s -> {
            categories = new ArrayList<>();
            subCategory = new ArrayList<>();
            regions = new ArrayList<>();
            districts = new ArrayList<>();
            categories.add(new Category(0, all));
            regions.add(new Region(0, all));
            subCategory.add(new ArrayList<>());
            subCategory.get(0).add(new Category(0, all));
            districts.add(new ArrayList<>());
            districts.get(0).add(new Region(0, all));

            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonCategories = jsonObject.getJSONArray("categories");
                JSONArray jsonRegions = jsonObject.getJSONArray("regions");

                for (int i = 0; i < jsonCategories.length() - 1; i++) {
                    JSONObject jsonCategory = jsonCategories.getJSONObject(i);
                    JSONArray jsonSubCategories = jsonCategory.getJSONArray("subcategories");
                    List<Category> tempSubCategories = new ArrayList<>();
                    Category tempCategory = new Category();
                    tempCategory.setId(jsonCategory.getLong("category_id"));
                    tempCategory.setName(jsonCategory.getString("title"));

                    for (int j = 0; j < jsonSubCategories.length(); j++) {
                        JSONObject jsonSubCategory = jsonSubCategories.getJSONObject(j);
                        Category subCategory = new Category();

                        subCategory.setId(jsonSubCategory.getLong("category_id"));
                        subCategory.setName(jsonSubCategory.getString("title"));
                        tempSubCategories.add(subCategory);
                    }

                    subCategory.add(tempSubCategories);
                    categories.add(tempCategory);
                }

                for (int i = 0; i < jsonRegions.length(); i++) {
                    JSONObject jsonRegion = jsonRegions.getJSONObject(i);
                    JSONArray jsonDistricts = jsonRegion.getJSONArray("districts");
                    List<Region> tempDistricts = new ArrayList<>();
                    tempDistricts.add(new Region(0, all));

                    Region tempRegion = new Region();
                    tempRegion.setId(jsonRegion.getLong("region_id"));
                    tempRegion.setName(jsonRegion.getString("title"));

                    for (int j = 0; j < jsonDistricts.length(); j++) {
                        JSONObject jsonDistrict = jsonDistricts.getJSONObject(j);
                        Region tempDistrict = new Region();
                        tempDistrict.setId(jsonDistrict.getLong("district_id"));
                        tempDistrict.setName(jsonDistrict.getString("title"));

                        tempDistricts.add(tempDistrict);
                    }

                    districts.add(tempDistricts);
                    regions.add(tempRegion);
                }

                view.showSearchData(categories, subCategory.get(0), regions, districts.get(0));
                view.showData();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, throwable -> {

        });
    }

    @Override
    public void getDistricts(int position) {
        if (position != -1 && districts.get(position).size() > 2) {
            view.changeDistricts(districts.get(position));
            view.showDistrictSpinner();
        } else {
            view.hideDistrictSpinner();
        }
    }

    @Override
    public void getSubCategory(int position) {
        if (position != -1) {
            view.changeSubCategory(subCategory.get(position));
        }
    }

    @Override
    public void search(String searchText, int categoryPosition, int subCategoryPosition, int regionPosition, int districtPosition) {
        Intent intent = new Intent();
        intent.putExtra(TITLE, categories.get(categoryPosition).getName());
        intent.putExtra(SEARCH_TEXT, searchText.trim().isEmpty() ? null : searchText);
        intent.putExtra(CATEGORY_ID, categoryPosition == 0 ? 0 : subCategory.get(categoryPosition).get(subCategoryPosition).getId());
        intent.putExtra(REGION_ID, regionPosition == 0 ? 0 : regions.get(regionPosition).getId());
        intent.putExtra(DISTRICT_ID, districtPosition == 0 ? 0 : districts.get(regionPosition).get(districtPosition).getId());

        view.openCompanyListActivity(intent);
    }

    @Override
    public void onDestroy() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        super.onDestroy();
    }
}
