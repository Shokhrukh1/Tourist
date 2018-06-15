package co.example.ui.discount.di;

import dagger.Binds;
import dagger.Module;
import co.example.config.scope.PerActivity;
import co.example.core.BaseActivityModule;
import co.example.ui.discount.DiscountActivity;
import co.example.ui.discount.DiscountPresenter;
import co.example.ui.discount.DiscountPresenterImpl;
import co.example.ui.discount.DiscountView;

/**
 * Created by Crish on 14.12.2017.
 */

@Module(includes = BaseActivityModule.class)
public abstract class DiscountActivityModule {
    @Binds
    @PerActivity
    abstract DiscountView provideDiscountView(DiscountActivity activity);

    @Binds
    @PerActivity
    abstract DiscountPresenter provideDiscountPresenter(DiscountPresenterImpl presenter);
}
