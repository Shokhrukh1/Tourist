package co.example.ui.signIn.di;

import dagger.Binds;
import dagger.Module;
import co.example.config.scope.PerActivity;
import co.example.core.BaseActivityModule;
import co.example.ui.signIn.SignInActivity;
import co.example.ui.signIn.SignInPresenter;
import co.example.ui.signIn.SignInPresenterImpl;
import co.example.ui.signIn.SignInView;

/**
 * Created by Crish on 18.12.2017.
 */

@Module(includes = BaseActivityModule.class)
public abstract class SignInActivityModule {
    @Binds
    @PerActivity
    abstract SignInView provideSignInView(SignInActivity activity);

    @Binds
    @PerActivity
    abstract SignInPresenter provideSignInPresenter(SignInPresenterImpl presenter);
}
