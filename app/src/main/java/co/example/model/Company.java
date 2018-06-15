package co.example.model;

import lombok.Data;

/**
 * Created by Crish on 22.12.2017.
 */

@Data
public class Company {
    private long saleId;
    private String title;
    private String address;
    private String phone;
    private String image;
    private double lat;
    private double lon;
    private String description;
    private String type;
    private String distance;
    private int btnType;
}
