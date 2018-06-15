package co.example.ui.signIn;

import co.example.core.Presenter;

/**
 * Created by Crish on 18.12.2017.
 */

public interface SignInPresenter extends Presenter {
    void signIn(String userName, String password);
}
