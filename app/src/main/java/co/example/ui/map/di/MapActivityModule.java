package co.example.ui.map.di;

import android.support.v7.app.AppCompatActivity;

import dagger.Binds;
import dagger.Module;
import co.example.config.scope.PerActivity;
import co.example.core.BaseActivityModule;
import co.example.ui.map.MapActivity;
import co.example.ui.map.MapPresenter;
import co.example.ui.map.MapPresenterImpl;
import co.example.ui.map.MapView;

/**
 * Created by Crish on 18.12.2017.
 */

@Module(includes = BaseActivityModule.class)
public abstract class MapActivityModule {
    @Binds
    @PerActivity
    abstract AppCompatActivity provideMapActivity(MapActivity activity);

    @Binds
    @PerActivity
    abstract MapView provideMapView(MapActivity activity);

    @Binds
    @PerActivity
    abstract MapPresenter provideMapPresenter(MapPresenterImpl presenter);
}
