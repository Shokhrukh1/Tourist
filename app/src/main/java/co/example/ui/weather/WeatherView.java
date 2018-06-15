package co.example.ui.weather;

import java.util.List;

import co.example.core.BaseView;
import co.example.model.weather.Weather;

/**
 * Created by Crish on 15.12.2017.
 */

public interface WeatherView extends BaseView {
    void showWeather(List<Weather> weatherList);
}
