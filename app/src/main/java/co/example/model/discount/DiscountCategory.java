package co.example.model.discount;

import lombok.Data;

/**
 * Created by Crish on 14.12.2017.
 */

@Data
public class DiscountCategory {
    private long categoryId;
    private String title;
    private String type;
    private String mdpi;
    private String hdpi;
    private String xhdpi;
    private String xxhdpi;
    private String xxxhdpi;
    private String color;
}
