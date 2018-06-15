package co.example.ui.category.di;

import dagger.Binds;
import dagger.Module;
import co.example.config.scope.PerActivity;
import co.example.core.BaseActivityModule;
import co.example.ui.category.CategoryActivity;
import co.example.ui.category.CategoryPresenter;
import co.example.ui.category.CategoryPresenterImpl;
import co.example.ui.category.CategoryView;

/**
 * Created by Crish on 29.12.2017.
 */

@Module(includes = BaseActivityModule.class)
public abstract class CategoryActivityModule {
    @Binds
    @PerActivity
    abstract CategoryView provideCategoryView(CategoryActivity activity);

    @Binds
    @PerActivity
    abstract CategoryPresenter provideCategoryPresenter(CategoryPresenterImpl presenter);
}
