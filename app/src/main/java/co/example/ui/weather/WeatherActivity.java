package co.example.ui.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import co.example.R;
import co.example.core.BaseActivity;
import co.example.model.weather.Weather;
import co.example.ui.weather.adapters.WeatherAdapter;
import co.example.utils.PreferencesHelper;
import co.example.utils.Utils;

public class WeatherActivity extends BaseActivity implements WeatherView {
    @Inject
    WeatherPresenter presenter;
    @BindView(R.id.rvWeather)
    RecyclerView rvWeather;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        toolbar.setTitle(R.string.weather);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_weather;
    }

    @Override
    public void showWeather(List<Weather> weatherList) {
        rvWeather.setLayoutManager(new LinearLayoutManager(this));
        rvWeather.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvWeather.setAdapter(new WeatherAdapter(weatherList));
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void getData() {
        if (Utils.isNetworkAvailable(this)) {
            presenter.getWeather();
        } else {
            showNoNetwork();
        }
    }
}
