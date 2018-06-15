package co.example.ui.restorePassword.di;

import dagger.Binds;
import dagger.Module;
import co.example.config.scope.PerActivity;
import co.example.core.BaseActivityModule;
import co.example.ui.restorePassword.RestorePasswordActivity;
import co.example.ui.restorePassword.RestorePasswordPresenter;
import co.example.ui.restorePassword.RestorePasswordPresenterImpl;
import co.example.ui.restorePassword.RestorePasswordView;

/**
 * Created by Crish on 19.12.2017.
 */

@Module(includes = BaseActivityModule.class)
public abstract class RestorePasswordActivityModule {
    @Binds
    @PerActivity
    abstract RestorePasswordView provideRestorePasswordView(RestorePasswordActivity activity);

    @Binds
    @PerActivity
    abstract RestorePasswordPresenter provideRestorePasswordPresenter(RestorePasswordPresenterImpl presenter);
}
