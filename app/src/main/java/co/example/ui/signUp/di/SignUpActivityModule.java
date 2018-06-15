package co.example.ui.signUp.di;

import dagger.Binds;
import dagger.Module;
import co.example.config.scope.PerActivity;
import co.example.core.BaseActivityModule;
import co.example.ui.signUp.SignUpActivity;
import co.example.ui.signUp.SignUpPresenter;
import co.example.ui.signUp.SignUpPresenterImpl;
import co.example.ui.signUp.SignUpView;

/**
 * Created by Crish on 18.12.2017.
 */

@Module(includes = BaseActivityModule.class)
public abstract class SignUpActivityModule {
    @Binds
    @PerActivity
    abstract SignUpView provideSignUpView(SignUpActivity activity);

    @Binds
    @PerActivity
    abstract SignUpPresenter provideSignUpPresenter(SignUpPresenterImpl presenter);
}
