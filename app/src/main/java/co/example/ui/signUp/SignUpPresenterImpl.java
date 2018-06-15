package co.example.ui.signUp;

import android.util.Log;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import co.example.core.BasePresenterImpl;
import co.example.network.ApiManager;
import co.example.utils.PreferencesHelper;

/**
 * Created by Crish on 18.12.2017.
 */

public class SignUpPresenterImpl extends BasePresenterImpl<SignUpView> implements SignUpPresenter {
    private ApiManager apiManager;
    private Disposable disposable;
    private PreferencesHelper preferencesHelper;

    @Inject
    public SignUpPresenterImpl(SignUpView view, ApiManager apiManager, PreferencesHelper preferencesHelper) {
        super(view);
        this.apiManager = apiManager;
        this.preferencesHelper = preferencesHelper;
    }

    @Override
    public void signUp(String login, String email, String name, String phone, String address, String password) {
        view.showLoadingDialog();

        disposable = apiManager.signUp(preferencesHelper.getLanguage(), login, email, name, phone, address, password).subscribe(s -> {
            Log.d("myLogs", s);
            view.clearViews();
            view.clearFocus();
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
