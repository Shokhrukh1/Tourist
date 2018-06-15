package co.example.ui.signUp;

import co.example.core.Presenter;

/**
 * Created by Crish on 18.12.2017.
 */

public interface SignUpPresenter extends Presenter {
    void signUp(String userName, String email, String name, String phone, String address, String password);
}
