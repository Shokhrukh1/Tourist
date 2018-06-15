package co.example.ui.homePage.di;

import dagger.Binds;
import dagger.Module;
import co.example.config.scope.PerActivity;
import co.example.core.BaseActivityModule;
import co.example.ui.homePage.HomeActivity;
import co.example.ui.homePage.HomePresenter;
import co.example.ui.homePage.HomePresenterImpl;
import co.example.ui.homePage.HomeView;

/**
 * Created by Portable-Acer on 06.12.2017.
 */

@Module(includes = BaseActivityModule.class)
public abstract class HomeActivityModule {
    @Binds
    @PerActivity
    abstract HomeView provideHomeView(HomeActivity activity);

    @Binds
    @PerActivity
    abstract HomePresenter provideHomePresenter(HomePresenterImpl presenter);
}
