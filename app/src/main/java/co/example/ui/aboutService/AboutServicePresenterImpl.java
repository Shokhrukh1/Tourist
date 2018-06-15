package co.example.ui.aboutService;

import android.text.Html;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import co.example.core.BasePresenterImpl;
import co.example.network.ApiManager;
import co.example.utils.PreferencesHelper;

/**
 * Created by Crish on 20.12.2017.
 */

public class AboutServicePresenterImpl extends BasePresenterImpl<AboutServiceView> implements AboutServicePresenter {
    private ApiManager apiManager;
    private Disposable disposable;
    private PreferencesHelper preferencesHelper;

    @Inject
    public AboutServicePresenterImpl(AboutServiceView view, ApiManager apiManager, PreferencesHelper preferencesHelper) {
        super(view);
        this.apiManager = apiManager;
        this.preferencesHelper = preferencesHelper;
    }

    @Override
    public void getAboutService() {
        view.showProgressBar();

        disposable = apiManager.getAboutService(preferencesHelper.getLanguage())
                .subscribe(s -> {
                    try {
                        view.showAboutService(Html.fromHtml(new JSONObject(s).getString("text")).toString());
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
