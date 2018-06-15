package co.example.ui.regionDescription.di;

import dagger.Binds;
import dagger.Module;
import co.example.config.scope.PerActivity;
import co.example.core.BaseActivityModule;
import co.example.ui.regionDescription.RegionDescriptionActivity;
import co.example.ui.regionDescription.RegionDescriptionPresenter;
import co.example.ui.regionDescription.RegionDescriptionPresenterImpl;
import co.example.ui.regionDescription.RegionDescriptionView;

/**
 * Created by Crish on 18.12.2017.
 */

@Module(includes = BaseActivityModule.class)
public abstract class RegionDescriptionActivityModule {
    @Binds
    @PerActivity
    abstract RegionDescriptionView provideRegionDescriptionView(RegionDescriptionActivity activity);

    @Binds
    @PerActivity
    abstract RegionDescriptionPresenter provideDescriptionPresenter(RegionDescriptionPresenterImpl presenter);
}
