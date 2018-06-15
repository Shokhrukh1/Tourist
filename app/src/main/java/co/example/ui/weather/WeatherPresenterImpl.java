package co.example.ui.weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import co.example.core.BasePresenterImpl;
import co.example.model.weather.Weather;
import co.example.model.weather.WeatherForecast;
import co.example.network.ApiManager;
import co.example.utils.PreferencesHelper;

/**
 * Created by Crish on 15.12.2017.
 */

public class WeatherPresenterImpl extends BasePresenterImpl<WeatherView> implements WeatherPresenter {
    private ApiManager apiManager;
    private Disposable disposable;
    private PreferencesHelper preferencesHelper;

    @Inject
    public WeatherPresenterImpl(WeatherView view, ApiManager apiManager, PreferencesHelper preferencesHelper) {
        super(view);
        this.apiManager = apiManager;
        this.preferencesHelper = preferencesHelper;
    }

    @Override
    public void getWeather() {
        view.showProgressBar();

        disposable = apiManager.getWeather(preferencesHelper.getLanguage()).subscribe(s -> {
            List<Weather> weathers = new ArrayList<>();

            try {
                JSONArray weatherList = new JSONArray(s);

                for (int i = 0; i < weatherList.length(); i++) {
                    JSONObject jsonObject = weatherList.getJSONObject(i);
                    JSONObject jsonCurrent = jsonObject.getJSONObject("current");
                    JSONArray forecast = jsonObject.getJSONArray("forecast");
                    List<WeatherForecast> weatherForecasts = new ArrayList<>();

                    Weather weather = new Weather();
                    weather.setLocation(jsonObject.getString("name"));
                    weather.setTime(jsonCurrent.getString("time"));
                    weather.setTemperature(jsonCurrent.getInt("temperature"));
                    weather.setImage(jsonCurrent.getString("image"));

                    for (int j = 0; j < forecast.length(); j++) {
                        JSONObject jsonObject1 = forecast.getJSONObject(j);
                        WeatherForecast weatherForecast = new WeatherForecast();
                        weatherForecast.setDate(jsonObject1.getString("date"));
                        weatherForecast.setDay(jsonObject1.getString("day"));
                        weatherForecast.setImage(jsonObject1.getString("image"));
                        weatherForecast.setLow(jsonObject1.getInt("low"));
                        weatherForecast.setHigh(jsonObject1.getInt("high"));

                        weatherForecasts.add(weatherForecast);
                    }

                    weather.setForecasts(weatherForecasts);
                    weathers.add(weather);
                }

                view.showWeather(weathers);
                view.showData();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, throwable -> {

        });
    }

    @Override
    public void onDestroy() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        super.onDestroy();
    }
}
