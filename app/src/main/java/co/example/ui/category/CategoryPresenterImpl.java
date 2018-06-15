package co.example.ui.category;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import co.example.core.BasePresenterImpl;
import co.example.model.category.Category;
import co.example.model.category.SubCategory;
import co.example.network.ApiManager;
import co.example.utils.PreferencesHelper;

/**
 * Created by Crish on 29.12.2017.
 */

public class CategoryPresenterImpl extends BasePresenterImpl<CategoryView> implements CategoryPresenter {
    private ApiManager apiManager;
    private Disposable disposable;
    private PreferencesHelper preferencesHelper;

    @Inject
    public CategoryPresenterImpl(CategoryView view, ApiManager apiManager, PreferencesHelper preferencesHelper) {
        super(view);
        this.apiManager = apiManager;
        this.preferencesHelper = preferencesHelper;
    }

    @Override
    public void getCategories(long parent) {
        view.showProgressBar();

        disposable = apiManager.getCategories(preferencesHelper.getLanguage(), parent)
                .subscribe(s -> {
                    Category category = new Category();
                    List<SubCategory> subCategories = new ArrayList<>();
                    category.setSubCategories(subCategories);

                    try {
                        JSONObject jsonData = new JSONObject(s).getJSONObject("data");
                        JSONArray jsonCategories = jsonData.getJSONArray("categories");

                        category.setImage(jsonData.getString("parent_image"));

                        for (int i = 0; i < jsonCategories.length(); i++) {
                            JSONObject jsonSubCategory = jsonCategories.getJSONObject(i);
                            JSONObject images = jsonSubCategory.getJSONObject("mobile_image").getJSONObject("android");
                            SubCategory subCategory = new SubCategory();

                            subCategory.setCategoryId(jsonSubCategory.getLong("category_id"));
                            subCategory.setTitle(jsonSubCategory.getString("title"));
                            subCategory.setType(jsonSubCategory.getString("type"));
                            subCategory.setMdpi(images.getString("mdpi"));
                            subCategory.setHdpi(images.getString("hdpi"));
                            subCategory.setXhdpi(images.getString("xhdpi"));
                            subCategory.setXxhdpi(images.getString("xxhdpi"));
                            subCategory.setXxxhdpi(images.getString("xxxhdpi"));
                            subCategory.setColor(images.getString("color"));

                            subCategories.add(subCategory);
                        }

                        view.showCategories(category);
                        view.showData();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, throwable -> {

                });
    }

    @Override
    public void onDestroy() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        super.onDestroy();
    }
}
