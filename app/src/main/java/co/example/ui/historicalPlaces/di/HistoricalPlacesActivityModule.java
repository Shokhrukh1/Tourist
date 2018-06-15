package co.example.ui.historicalPlaces.di;

import dagger.Binds;
import dagger.Module;
import co.example.config.scope.PerActivity;
import co.example.core.BaseActivityModule;
import co.example.ui.historicalPlaces.HistoricalPlacesActivity;
import co.example.ui.historicalPlaces.HistoricalPlacesPresenter;
import co.example.ui.historicalPlaces.HistoricalPlacesPresenterImpl;
import co.example.ui.historicalPlaces.HistoricalPlacesView;

/**
 * Created by Crish on 11.12.2017.
 */

@Module(includes = BaseActivityModule.class)
public abstract class HistoricalPlacesActivityModule {
    @Binds
    @PerActivity
    abstract HistoricalPlacesView provideHistoricalPlacesView(HistoricalPlacesActivity activity);

    @Binds
    @PerActivity
    abstract HistoricalPlacesPresenter provideHistoricalPlacesPresenter(HistoricalPlacesPresenterImpl presenter);
}
