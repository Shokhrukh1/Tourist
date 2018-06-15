package co.example.ui.companyList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import co.example.core.BasePresenterImpl;
import co.example.model.Company;
import co.example.network.ApiManager;
import co.example.utils.PreferencesHelper;

/**
 * Created by Crish on 21.12.2017.
 */

public class CompanyListPresenterImpl extends BasePresenterImpl<CompanyListView> implements CompanyListPresenter {
    private ApiManager apiManager;
    private Disposable disposable;
    private boolean isLastPage = false;
    private int currentPage = 0;
    private List<Company> companies;
    private PreferencesHelper preferencesHelper;

    @Inject
    public CompanyListPresenterImpl(CompanyListView view, ApiManager apiManager, PreferencesHelper preferencesHelper) {
        super(view);
        this.apiManager = apiManager;
        this.preferencesHelper = preferencesHelper;
        companies = new ArrayList<>();
    }

    @Override
    public void getCompanies(String searchText, Long categoryId, Long regionId, Long districtId, double latitude, double longitude) {
        if (companies.isEmpty()) {
            view.showProgressBar();

            disposable = apiManager.getCompanies(preferencesHelper.getLanguage(), searchText,
                    categoryId == 0 ? null : categoryId,
                    regionId == 0 ? null : regionId,
                    districtId == 0 ? null : districtId, ++currentPage, latitude, longitude)
                    .subscribe(s -> {
                        try {
                            List<Company> tempCompanies = new ArrayList<>();
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            isLastPage = jsonObject.getBoolean("last_page");

                            if (jsonArray.length() == 0) {
                                view.showNoData();
                                return;
                            }

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                Company company = new Company();
                                company.setSaleId(jsonObject1.getLong("sale_id"));
                                company.setTitle(jsonObject1.getString("title"));
                                company.setAddress(jsonObject1.getString("address"));
                                company.setPhone(jsonObject1.getString("phone"));
                                company.setImage(jsonObject1.getString("image"));
                                company.setDescription(jsonObject1.getString("description"));
                                company.setType(jsonObject1.getString("type"));
                                company.setDistance(jsonObject1.getString("distance"));
                                company.setBtnType(jsonObject1.getInt("btn_type"));

                                if (jsonObject1.isNull("lat") || jsonObject1.isNull("lon")) {
                                    continue;
                                } else {
                                    company.setLat(jsonObject1.getDouble("lat"));
                                    company.setLon(jsonObject1.getDouble("lon"));
                                }

                                tempCompanies.add(company);
                            }

                            companies.addAll(tempCompanies);
                            view.showCompanies(tempCompanies);
                            view.showData();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, throwable -> {
                    });
        }
    }

    @Override
    public void getMoreCompanies(String searchText, Long categoryId, Long regionId, Long districtId, double latitude, double longitude) {
        if (!isLastPage) {
            view.showLoadMoreProgressBar();
            disposable = apiManager.getCompanies(preferencesHelper.getLanguage(), searchText,
                    categoryId == 0 ? null : categoryId,
                    regionId == 0 ? null : regionId,
                    districtId == 0 ? null : districtId, ++currentPage, latitude, longitude)
                    .subscribe(s -> {
                        try {
                            List<Company> tempCompanies = new ArrayList<>();
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            isLastPage = jsonObject.getBoolean("last_page");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                Company company = new Company();
                                company.setSaleId(jsonObject1.getLong("sale_id"));
                                company.setTitle(jsonObject1.getString("title"));
                                company.setAddress(jsonObject1.getString("address"));
                                company.setPhone(jsonObject1.getString("phone"));
                                company.setImage(jsonObject1.getString("image"));
                                company.setDescription(jsonObject1.getString("description"));
                                company.setType(jsonObject1.getString("type"));
                                company.setDistance(jsonObject1.getString("distance"));
                                company.setBtnType(jsonObject1.getInt("btn_type"));

                                if (jsonObject1.isNull("lat") || jsonObject1.isNull("lon")) {
                                    continue;
                                } else {
                                    company.setLat(jsonObject1.getDouble("lat"));
                                    company.setLon(jsonObject1.getDouble("lon"));
                                }

                                tempCompanies.add(company);
                            }

                            companies.addAll(tempCompanies);
                            view.hideLoadMoreProgressBar();
                            view.showMoreCompanies(tempCompanies);
                            view.setLoaded();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            view.hideLoadMoreProgressBar();
                            view.setLoaded();
                        }
                    }, throwable -> {
                    });
        }
    }

    @Override
    public void onDestroy() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        super.onDestroy();
    }
}
