package co.example.ui.weather.adapters;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import co.example.R;
import co.example.core.BaseAdapter;
import co.example.core.BaseViewHolder;
import co.example.model.weather.Weather;

public class WeatherAdapter extends BaseAdapter<Weather, WeatherAdapter.WeatherViewHolder> {
    public WeatherAdapter(List<Weather> items) {
        super(items);
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);

        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        int temperature = items.get(position).getTemperature();

        holder.tvRegion.setText(items.get(position).getLocation());
        holder.tvDate.setText(items.get(position).getTime());
        holder.tvTemperature.setText(temperature > 0 ? "+" + temperature : "" + temperature);

        Glide.with(holder.itemView.getContext())
                .load(items.get(position).getImage())
                .into(holder.ivWeather);

        holder.rvWeatherForecast.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        holder.rvWeatherForecast.setAdapter(new WeatherForecastAdapter(items.get(position).getForecasts()));
    }

    class WeatherViewHolder extends BaseViewHolder {
        @BindView(R.id.tvRegion)
        TextView tvRegion;
        @BindView(R.id.tvDate)
        TextView tvDate;
        @BindView(R.id.tvTemperature)
        TextView tvTemperature;
        @BindView(R.id.ivWeather)
        ImageView ivWeather;
        @BindView(R.id.rvWeatherForecast)
        RecyclerView rvWeatherForecast;

        public WeatherViewHolder(View itemView) {
            super(itemView);
        }
    }
}
