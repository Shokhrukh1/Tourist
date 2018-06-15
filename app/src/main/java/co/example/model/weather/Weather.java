package co.example.model.weather;

import java.util.List;

import lombok.Data;

/**
 * Created by Crish on 15.12.2017.
 */

@Data
public class Weather {
    private String location;
    private String time;
    private int temperature;
    private String image;
    private List<WeatherForecast> forecasts;
}
