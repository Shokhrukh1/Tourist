package co.example.model.weather;

import lombok.Data;

/**
 * Created by Crish on 15.12.2017.
 */

@Data
public class WeatherForecast {
    private String date;
    private int low;
    private int high;
    private String image;
    private String day;
}
