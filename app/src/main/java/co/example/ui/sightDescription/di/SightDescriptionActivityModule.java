package co.example.ui.sightDescription.di;

import dagger.Binds;
import dagger.Module;
import co.example.config.scope.PerActivity;
import co.example.core.BaseActivityModule;
import co.example.ui.sightDescription.SightDescriptionActivity;
import co.example.ui.sightDescription.SightDescriptionPresenter;
import co.example.ui.sightDescription.SightDescriptionPresenterImpl;
import co.example.ui.sightDescription.SightDescriptionView;

/**
 * Created by Crish on 27.12.2017.
 */

@Module(includes = BaseActivityModule.class)
public abstract class SightDescriptionActivityModule {
    @Binds
    @PerActivity
    abstract SightDescriptionView provideSightDescriptionView(SightDescriptionActivity activity);

    @Binds
    @PerActivity
    abstract SightDescriptionPresenter provideSightDescriptionPresenter(SightDescriptionPresenterImpl presenter);
}
