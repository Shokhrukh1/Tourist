package co.example.ui.activateDiscountCard.di;

import dagger.Binds;
import dagger.Module;
import co.example.config.scope.PerActivity;
import co.example.core.BaseActivityModule;
import co.example.ui.activateDiscountCard.ActivateDiscountCardActivity;
import co.example.ui.activateDiscountCard.ActivateDiscountCardPresenter;
import co.example.ui.activateDiscountCard.ActivateDiscountCardPresenterImpl;
import co.example.ui.activateDiscountCard.ActivateDiscountCardView;

/**
 * Created by Crish on 20.12.2017.
 */

@Module(includes = BaseActivityModule.class)
public abstract class ActivateDiscountCardActivityModule {
    @Binds
    @PerActivity
    abstract ActivateDiscountCardView provideActivateDiscountCardView(ActivateDiscountCardActivity activity);

    @Binds
    @PerActivity
    abstract ActivateDiscountCardPresenter provideActivateDiscountCardPresenter(ActivateDiscountCardPresenterImpl presenter);
}
