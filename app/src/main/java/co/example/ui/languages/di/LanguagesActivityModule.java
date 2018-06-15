package co.example.ui.languages.di;

import dagger.Binds;
import dagger.Module;
import co.example.config.scope.PerActivity;
import co.example.core.BaseActivityModule;
import co.example.ui.languages.LanguagesActivity;
import co.example.ui.languages.LanguagesPresenter;
import co.example.ui.languages.LanguagesPresenterImpl;
import co.example.ui.languages.LanguagesView;

/**
 * Created by Crish on 28.12.2017.
 */

@Module(includes = BaseActivityModule.class)
public abstract class LanguagesActivityModule {
    @Binds
    @PerActivity
    abstract LanguagesView provideLanguagesView(LanguagesActivity activity);

    @Binds
    @PerActivity
    abstract LanguagesPresenter provideLanguagesPresenter(LanguagesPresenterImpl presenter);
}
