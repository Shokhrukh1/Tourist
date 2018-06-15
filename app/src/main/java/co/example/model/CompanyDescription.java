package co.example.model;

import java.util.List;

import lombok.Data;

/**
 * Created by Crish on 26.12.2017.
 */

@Data
public class CompanyDescription {
    private long saleId;
    private boolean hasDiscount;
    private double latitude;
    private double longitude;
    private String email;
    private List<String> phones;
    private String companyName;
    private String address;
    private String description;
    private String landmark;
    private String text;
    private String categoryTitle;
    private String regionTitle;
    private String districtTitle;
    private String type;
    private List<String> images;
}
