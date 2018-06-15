package co.example.ui.favorite.di;

import dagger.Binds;
import dagger.Module;
import co.example.config.scope.PerActivity;
import co.example.core.BaseActivityModule;
import co.example.ui.favorite.FavoriteActivity;
import co.example.ui.favorite.FavoritePresenter;
import co.example.ui.favorite.FavoritePresenterImpl;
import co.example.ui.favorite.FavoriteView;

/**
 * Created by crish on 1/11/18.
 */

@Module(includes = BaseActivityModule.class)
public abstract class FavoriteActivityModule {
    @Binds
    @PerActivity
    abstract FavoriteView provideFavoriteView(FavoriteActivity activity);

    @Binds
    @PerActivity
    abstract FavoritePresenter provideFavoritePresenter(FavoritePresenterImpl presenter);
}
