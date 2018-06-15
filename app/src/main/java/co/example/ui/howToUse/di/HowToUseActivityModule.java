package co.example.ui.howToUse.di;

import dagger.Binds;
import dagger.Module;
import co.example.config.scope.PerActivity;
import co.example.core.BaseActivityModule;
import co.example.ui.howToUse.HowToUseActivity;
import co.example.ui.howToUse.HowToUsePresenter;
import co.example.ui.howToUse.HowToUsePresenterImpl;
import co.example.ui.howToUse.HowToUseView;

/**
 * Created by Crish on 20.12.2017.
 */

@Module(includes = BaseActivityModule.class)
public abstract class HowToUseActivityModule {
    @Binds
    @PerActivity
    abstract HowToUseView provideHowToUseView(HowToUseActivity activity);

    @Binds
    @PerActivity
    abstract HowToUsePresenter provideHowToUsePresenter(HowToUsePresenterImpl presenter);
}
