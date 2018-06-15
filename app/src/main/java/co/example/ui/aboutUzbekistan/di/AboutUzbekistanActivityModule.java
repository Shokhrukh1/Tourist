package co.example.ui.aboutUzbekistan.di;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import co.example.config.scope.PerActivity;
import co.example.core.BaseActivityModule;
import co.example.ui.aboutUzbekistan.AboutUzbekistanActivity;
import co.example.ui.aboutUzbekistan.AboutUzbekistanPresenter;
import co.example.ui.aboutUzbekistan.AboutUzbekistanPresenterImpl;
import co.example.ui.aboutUzbekistan.AboutUzbekistanView;

/**
 * Created by Crish on 11.12.2017.
 */

@Module(includes = BaseActivityModule.class)
public abstract class AboutUzbekistanActivityModule {
    @Binds
    @PerActivity
    abstract AboutUzbekistanView provideAboutUzbekistanView(AboutUzbekistanActivity activity);

    @Binds
    @PerActivity
    abstract AboutUzbekistanPresenter provideAboutUzbekistanPresenter(AboutUzbekistanPresenterImpl presenter);
}
