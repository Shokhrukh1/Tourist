package co.example.model.category;

import lombok.Data;

/**
 * Created by Crish on 29.12.2017.
 */

@Data
public class SubCategory {
    private long categoryId;
    private String title;
    private String type;
    private String hdpi;
    private String mdpi;
    private String xhdpi;
    private String xxhdpi;
    private String xxxhdpi;
    private String color;
}
