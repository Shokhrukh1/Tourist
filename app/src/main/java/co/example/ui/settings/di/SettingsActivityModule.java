package co.example.ui.settings.di;

import dagger.Binds;
import dagger.Module;
import co.example.config.scope.PerActivity;
import co.example.core.BaseActivityModule;
import co.example.ui.settings.SettingsActivity;
import co.example.ui.settings.SettingsPresenter;
import co.example.ui.settings.SettingsPresenterImpl;
import co.example.ui.settings.SettingsView;

/**
 * Created by Crish on 28.12.2017.
 */

@Module(includes = BaseActivityModule.class)
public abstract class SettingsActivityModule {
    @Binds
    @PerActivity
    abstract SettingsView provideSettingsView(SettingsActivity activity);

    @Binds
    @PerActivity
    abstract SettingsPresenter provideSettingsPresenter(SettingsPresenterImpl presenter);
}
