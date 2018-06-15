package co.example.ui.publicOffer;

import android.text.Html;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import co.example.core.BasePresenterImpl;
import co.example.network.ApiManager;
import co.example.utils.PreferencesHelper;

/**
 * Created by Crish on 20.12.2017.
 */

public class PublicOfferPresenterImpl extends BasePresenterImpl<PublicOfferView> implements PublicOfferPresenter {
    private ApiManager apiManager;
    private Disposable disposable;
    private PreferencesHelper preferencesHelper;

    @Inject
    public PublicOfferPresenterImpl(PublicOfferView view, ApiManager apiManager, PreferencesHelper preferencesHelper) {
        super(view);
        this.apiManager = apiManager;
        this.preferencesHelper = preferencesHelper;
    }

    @Override
    public void getPublicOffer() {
        view.showProgressBar();

        disposable = apiManager.getPublicOffer(preferencesHelper.getLanguage()).subscribe(s -> {
            try {
                view.showPublicOffer(Html.fromHtml(new JSONObject(s).getString("text")).toString());
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
