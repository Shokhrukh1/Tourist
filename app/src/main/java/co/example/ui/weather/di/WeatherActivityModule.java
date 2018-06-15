package co.example.ui.weather.di;

import dagger.Binds;
import dagger.Module;
import co.example.config.scope.PerActivity;
import co.example.core.BaseActivityModule;
import co.example.ui.weather.WeatherActivity;
import co.example.ui.weather.WeatherPresenter;
import co.example.ui.weather.WeatherPresenterImpl;
import co.example.ui.weather.WeatherView;

/**
 * Created by Crish on 15.12.2017.
 */

@Module(includes = BaseActivityModule.class)
public abstract class WeatherActivityModule {
    @Binds
    @PerActivity
    abstract WeatherView provideWeatherView(WeatherActivity activity);

    @Binds
    @PerActivity
    abstract WeatherPresenter provideWeatherPresenter(WeatherPresenterImpl presenter);
}
