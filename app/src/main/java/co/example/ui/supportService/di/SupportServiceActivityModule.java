package co.example.ui.supportService.di;

import dagger.Binds;
import dagger.Module;
import co.example.config.scope.PerActivity;
import co.example.ui.supportService.SupportServiceActivity;
import co.example.ui.supportService.SupportServicePresenter;
import co.example.ui.supportService.SupportServicePresenterImpl;
import co.example.ui.supportService.SupportServiceView;

/**
 * Created by Crish on 14.12.2017.
 */

@Module
public abstract class SupportServiceActivityModule {
    @Binds
    @PerActivity
    abstract SupportServiceView provideSupportServiceView(SupportServiceActivity activity);

    @Binds
    @PerActivity
    abstract SupportServicePresenter provideSupportServicePresenter(SupportServicePresenterImpl presenter);
}
