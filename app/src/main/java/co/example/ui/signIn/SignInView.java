package co.example.ui.signIn;

import co.example.core.BaseView;

/**
 * Created by Crish on 18.12.2017.
 */

public interface SignInView extends BaseView {
    void clearViews();
    void showSignInSuccessful();
    void showSignInFailed(String error);
}
