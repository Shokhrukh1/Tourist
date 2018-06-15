package co.example.ui.publicOffer.di;

import dagger.Binds;
import dagger.Module;
import co.example.config.scope.PerActivity;
import co.example.core.BaseActivityModule;
import co.example.ui.publicOffer.PublicOfferActivity;
import co.example.ui.publicOffer.PublicOfferPresenter;
import co.example.ui.publicOffer.PublicOfferPresenterImpl;
import co.example.ui.publicOffer.PublicOfferView;

/**
 * Created by Crish on 20.12.2017.
 */

@Module(includes = BaseActivityModule.class)
public abstract class PublicOfferActivityModule {
    @Binds
    @PerActivity
    abstract PublicOfferView providePublicOfferView(PublicOfferActivity activity);

    @Binds
    @PerActivity
    abstract PublicOfferPresenter providePublicOfferPresenter(PublicOfferPresenterImpl presenter);
}
