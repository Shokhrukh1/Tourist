package co.example.ui.companyDescription;

import android.text.Html;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import co.example.core.BasePresenterImpl;
import co.example.model.CompanyDescription;
import co.example.network.ApiManager;
import co.example.utils.PreferencesHelper;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by Crish on 25.12.2017.
 */

public class CompanyDescriptionPresenterImpl extends BasePresenterImpl<CompanyDescriptionView> implements CompanyDescriptionPresenter {
    private ApiManager apiManager;
    private CompanyDescription companyDescription;
    private Disposable disposable;
    private Disposable disposableFavorite;
    private boolean isAddedFavorite;
    private PreferencesHelper preferencesHelper;

    @Inject
    public CompanyDescriptionPresenterImpl(CompanyDescriptionView view, ApiManager apiManager, PreferencesHelper preferencesHelper) {
        super(view);
        this.apiManager = apiManager;
        this.preferencesHelper = preferencesHelper;
    }

    @Override
    public void getCompanyDescription(long saleId) {
        view.showProgressBar();
        Log.d("myLogs", "saleId = " + saleId);

        disposable = apiManager.getCompanyDescription(preferencesHelper.getLanguage(), saleId)
                .subscribe(s -> {
                    CompanyDescription description = new CompanyDescription();
                    List<String> images = new ArrayList<>();
                    List<String> phones = new ArrayList<>();

                    try {
                        JSONObject jsonObject = new JSONObject(s);
                        description.setSaleId(jsonObject.getLong("sale_id"));
                        description.setHasDiscount(jsonObject.getBoolean("has_discount"));
                        description.setLatitude(jsonObject.getDouble("lat"));
                        description.setLongitude(jsonObject.getDouble("lon"));
                        description.setEmail(jsonObject.getString("email"));
                        description.setCompanyName(jsonObject.getString("title"));
                        description.setAddress(jsonObject.getString("address"));
                        description.setDescription(jsonObject.getString("description"));
                        description.setLandmark(jsonObject.getString("landmark"));
                        description.setText(Html.fromHtml(jsonObject.getString("text")).toString());
                        description.setCategoryTitle(jsonObject.getString("category_title"));
                        description.setRegionTitle(jsonObject.getString("region_title"));
                        description.setDistrictTitle(jsonObject.getString("district_title"));
                        description.setType(jsonObject.getString("type"));

                        JSONArray gallery = jsonObject.getJSONArray("gallery");

                        for (int i = 0; i < gallery.length(); i++) {
                            images.add(gallery.getString(i));
                        }

                        phones.addAll(Arrays.asList(jsonObject.getString("phone").trim().split(",|;")));

                        description.setPhones(phones);
                        description.setImages(images);

                        companyDescription = description;
                        view.showCompanyDescription(description);
                        view.showData();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, throwable -> {

                });
    }

    @Override
    public void getPhones() {
        if (companyDescription.getPhones().isEmpty()) {
            view.showAlertDialog();
        } else {
            view.showCallDialog(companyDescription.getPhones());
        }
    }

    @Override
    public void checkAddedFavorite(long saleId) {
        disposableFavorite = apiManager.getFavorites(preferencesHelper.getLanguage(),
                preferencesHelper.getUserId()).subscribe(s -> {
            JSONArray jsonResponse = new JSONArray(s);

            for (int i = 0; i < jsonResponse.length(); i++) {
                long tempSaleId = jsonResponse.getJSONObject(i).getLong("sale_id");
                if (saleId == tempSaleId) {
                    isAddedFavorite = true;
                    view.showGoldFavorite();

                    return;
                }
            }

            view.showWhiteFavorite();
        }, throwable -> {
        });
    }

    @Override
    public void addFavorite(long saleId) {
        view.showFavoriteLoading();

        if (!preferencesHelper.containsUserId()) {
            view.showPleaseSignIn();
            view.showWhiteFavorite();

            return;
        }

        disposableFavorite = apiManager.addFavorite(preferencesHelper.getUserId(), saleId).subscribe(s -> {
            if (Integer.parseInt(s) == 1) {
                isAddedFavorite = true;
                view.showGoldFavorite();
                view.showFavoriteAddedSuccessfully();
                view.prepareActivityResult(RESULT_CANCELED, saleId);
            } else {
                view.showWhiteFavorite();
                view.showFavoriteAddFailed();
            }
        }, throwable -> {
            Log.d("myLogs", throwable.getMessage());
        });
    }

    @Override
    public void removeFavorite(long saleId) {
        view.showFavoriteLoading();

        if (!preferencesHelper.containsUserId()) {
            view.showPleaseSignIn();
            view.showWhiteFavorite();

            return;
        }

        disposableFavorite = apiManager.removeFavorite(preferencesHelper.getUserId(), saleId).subscribe(s -> {
            if (Integer.parseInt(s) == 1) {
                isAddedFavorite = false;
                view.showWhiteFavorite();
                view.showFavoriteRemovedSuccessfully();
                view.prepareActivityResult(RESULT_OK, saleId);
            } else {
                view.showGoldFavorite();
                view.showFavoriteRemoveFailed();
            }
        }, throwable -> {
        });
    }

    @Override
    public boolean isAddedFavorite() {
        return isAddedFavorite;
    }

    @Override
    public void onDestroy() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }

        if (disposableFavorite != null && !disposableFavorite.isDisposed()) {
            disposableFavorite.dispose();
        }
        super.onDestroy();
    }
}
