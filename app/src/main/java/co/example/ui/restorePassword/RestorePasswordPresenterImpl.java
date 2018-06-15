package co.example.ui.restorePassword;

import org.json.JSONObject;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import co.example.core.BasePresenterImpl;
import co.example.network.ApiManager;
import co.example.utils.PreferencesHelper;

/**
 * Created by Crish on 19.12.2017.
 */

public class RestorePasswordPresenterImpl extends BasePresenterImpl<RestorePasswordView> implements RestorePasswordPresenter {
    private ApiManager apiManager;
    private Disposable disposable;
    private PreferencesHelper preferencesHelper;

    @Inject
    public RestorePasswordPresenterImpl(RestorePasswordView view, ApiManager apiManager, PreferencesHelper preferencesHelper) {
        super(view);
        this.apiManager = apiManager;
        this.preferencesHelper = preferencesHelper;
    }

    @Override
    public void restorePassword(String email) {
        disposable = apiManager.restorePassword(preferencesHelper.getLanguage(), email).subscribe(s -> {
            JSONObject jsonResponse = new JSONObject(s);

            if (jsonResponse.getInt("code") == 1) {
                view.showRestorePasswordSuccessful();
                view.clearViews();
            } else {
                view.showRestorePasswordFailed(jsonResponse.getString("message"));
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
