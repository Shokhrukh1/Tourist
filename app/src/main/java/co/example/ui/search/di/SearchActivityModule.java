package co.example.ui.search.di;

import android.content.Context;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import co.example.R;
import co.example.config.scope.PerActivity;
import co.example.core.BaseActivityModule;
import co.example.ui.search.SearchActivity;
import co.example.ui.search.SearchPresenter;
import co.example.ui.search.SearchPresenterImpl;
import co.example.ui.search.SearchView;

/**
 * Created by Crish on 27.12.2017.
 */

@Module(includes = BaseActivityModule.class)
public abstract class SearchActivityModule {
    @Binds
    @PerActivity
    abstract SearchView provideSearchView(SearchActivity activity);

    @Binds
    @PerActivity
    abstract SearchPresenter provideSearchPresenter(SearchPresenterImpl presenter);

    @PerActivity
    @Provides
    @Named(value = "all")
    static String provideAll(SearchActivity activity) {
        return activity.getResources().getString(R.string.all);
    }
}
