package co.example.ui.touristInformationCard.di;

import butterknife.BindView;
import dagger.Binds;
import dagger.Module;
import co.example.config.scope.PerActivity;
import co.example.core.BaseActivityModule;
import co.example.ui.touristInformationCard.TouristInformationCardActivity;
import co.example.ui.touristInformationCard.TouristInformationCardPresenter;
import co.example.ui.touristInformationCard.TouristInformationCardPresenterImpl;
import co.example.ui.touristInformationCard.TouristInformationCardView;

/**
 * Created by Crish on 20.12.2017.
 */

@Module(includes = BaseActivityModule.class)
public abstract class TouristInformationCardActivityModule {
    @Binds
    @PerActivity
    abstract TouristInformationCardView provideTouristInformationCardView(TouristInformationCardActivity activity);

    @Binds
    @PerActivity
    abstract TouristInformationCardPresenter provideTouristInformationCardPresenter(TouristInformationCardPresenterImpl presenter);
}
