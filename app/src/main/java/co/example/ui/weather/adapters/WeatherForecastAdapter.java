package co.example.ui.weather.adapters;

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
import co.example.model.weather.WeatherForecast;

public class WeatherForecastAdapter extends BaseAdapter<WeatherForecast, WeatherForecastAdapter.WeatherForecastViewHolder> {
    public WeatherForecastAdapter(List<WeatherForecast> items) {
        super(items);
    }

    @Override
    public WeatherForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather_forecast, parent, false);

        return new WeatherForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherForecastViewHolder holder, int position) {
        int lowTemp = items.get(position).getLow();
        int highTemp = items.get(position).getHigh();

        holder.tvDay.setText(items.get(position).getDay());
        holder.tvDate.setText(items.get(position).getDate());
        holder.tvHighTemp.setText(highTemp > 0 ? "+" + highTemp : "" + highTemp);
        holder.tvLowTemp.setText(lowTemp > 0 ? "+" + lowTemp : "" + lowTemp);

        Glide.with(holder.itemView.getContext())
                .load(items.get(position).getImage())
                .into(holder.ivWeather);
    }

    class WeatherForecastViewHolder extends BaseViewHolder {
        @BindView(R.id.tvDay)
        TextView tvDay;
        @BindView(R.id.tvDate)
        TextView tvDate;
        @BindView(R.id.ivWeather)
        ImageView ivWeather;
        @BindView(R.id.tvLowTemp)
        TextView tvLowTemp;
        @BindView(R.id.tvHighTemp)
        TextView tvHighTemp;

        public WeatherForecastViewHolder(View itemView) {
            super(itemView);
        }
    }
}
