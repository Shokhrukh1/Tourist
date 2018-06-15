package co.example.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by Crish on 11.12.2017.
 */

@Data
public class AboutUzbekistan {
    @SerializedName("page_id")
    private long pageId;
    @SerializedName("image")
    private String imageUrl;
    @SerializedName("template")
    private String template;
    @SerializedName("title")
    private String title;
    @SerializedName("text")
    private String text;
}
