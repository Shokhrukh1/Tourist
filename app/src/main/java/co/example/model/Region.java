package co.example.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by Crish on 11.12.2017.
 */

@Data
public class Region {
    @SerializedName("region_id")
    private long regionId;
    @SerializedName("image")
    private String imageUrl;
    @SerializedName("title")
    private String title;
    @SerializedName("name")
    private String name;
}
