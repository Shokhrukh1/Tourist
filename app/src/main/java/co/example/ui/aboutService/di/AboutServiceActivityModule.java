package co.example.ui.aboutService.di;

import dagger.Binds;
import dagger.Module;
import co.example.config.scope.PerActivity;
import co.example.core.BaseActivityModule;
import co.example.ui.aboutService.AboutServiceActivity;
import co.example.ui.aboutService.AboutServicePresenter;
import co.example.ui.aboutService.AboutServicePresenterImpl;
import co.example.ui.aboutService.AboutServiceView;
import co.example.utils.PreferencesHelper;

/**
 * Created by Crish on 20.12.2017.
 */

@Module(includes = BaseActivityModule.class)
public abstract class AboutServiceActivityModule {
    @Binds
    @PerActivity
    abstract AboutServiceView provideAboutServiceView(AboutServiceActivity activity);

    @Binds
    @PerActivity
    abstract AboutServicePresenter provideAboutServicePresenter(AboutServicePresenterImpl presenter);
}