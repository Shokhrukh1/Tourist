package co.example.ui.signIn;

import org.json.JSONObject;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import co.example.core.BasePresenterImpl;
import co.example.network.ApiManager;
import co.example.utils.PreferencesHelper;

/**
 * Created by Crish on 18.12.2017.
 */

public class SignInPresenterImpl extends BasePresenterImpl<SignInView> implements SignInPresenter {
    private ApiManager apiManager;
    private Disposable disposable;
    private PreferencesHelper preferencesHelper;

    @Inject
    public SignInPresenterImpl(SignInView view, ApiManager apiManager, PreferencesHelper preferencesHelper) {
        super(view);
        this.apiManager = apiManager;
        this.preferencesHelper = preferencesHelper;
    }

    @Override
    public void signIn(String userName, String password) {
        view.showLoadingDialog();

        disposable = apiManager.signIn(preferencesHelper.getLanguage(), userName, password).subscribe(s -> {
            JSONObject jsonResult = new JSONObject(s);

            if (jsonResult.getInt("code") == 1) {
                view.showSignInSuccessful();
                view.clearViews();
                preferencesHelper.setUserId(jsonResult.getLong("id"));
            } else {
                view.showSignInFailed(jsonResult.getString("message"));
            }

            view.clearFocus();
            view.hideLoadingDialog();
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
