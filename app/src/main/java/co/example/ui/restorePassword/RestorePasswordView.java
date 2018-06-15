package co.example.ui.restorePassword;

import co.example.core.BaseView;

/**
 * Created by Crish on 19.12.2017.
 */

public interface RestorePasswordView extends BaseView {
    void clearViews();
    void showRestorePasswordSuccessful();
    void showRestorePasswordFailed(String error);
}
