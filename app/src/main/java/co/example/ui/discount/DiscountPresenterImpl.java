package co.example.ui.discount;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import co.example.core.BasePresenterImpl;
import co.example.model.discount.Discount;
import co.example.model.discount.DiscountCategory;
import co.example.network.ApiManager;
import co.example.utils.PreferencesHelper;

/**
 * Created by Crish on 14.12.2017.
 */

public class DiscountPresenterImpl extends BasePresenterImpl<DiscountView> implements DiscountPresenter {
    private ApiManager apiManager;
    private Disposable disposable;
    private PreferencesHelper preferencesHelper;

    @Inject
    public DiscountPresenterImpl(DiscountView view, ApiManager apiManager, PreferencesHelper preferencesHelper) {
        super(view);
        this.apiManager = apiManager;
        this.preferencesHelper = preferencesHelper;
    }

    @Override
    public void getDiscounts() {
        view.showProgressBar();

        disposable = apiManager.getDiscounts(preferencesHelper.getLanguage())
                .subscribe(s -> {
                    Discount discount1 = new Discount();
                    List<DiscountCategory> discountCategories = new ArrayList<>();
                    try {
                        JSONObject data = new JSONObject(s).getJSONObject("data");
                        JSONArray categories = data.getJSONArray("categories");

                        discount1.setParentImage(data.getString("parent_image"));

                        for (int i = 0; i < categories.length(); i++) {
                            JSONObject category = categories.getJSONObject(i);
                            JSONObject android = category.getJSONObject("mobile_image").getJSONObject("android");
                            DiscountCategory discountCategory = new DiscountCategory();
                            discountCategory.setCategoryId(category.getLong("category_id"));
                            discountCategory.setTitle(category.getString("title"));
                            discountCategory.setType(category.getString("type"));
                            discountCategory.setMdpi(android.getString("mdpi"));
                            discountCategory.setHdpi(android.getString("hdpi"));
                            discountCategory.setXhdpi(android.getString("xhdpi"));
                            discountCategory.setXxhdpi(android.getString("xxhdpi"));
                            discountCategory.setXxxhdpi(android.getString("xxxhdpi"));

                            discountCategories.add(discountCategory);
                        }

                        discount1.setDiscountCategories(discountCategories);

                        view.showDiscounts(discount1);
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
