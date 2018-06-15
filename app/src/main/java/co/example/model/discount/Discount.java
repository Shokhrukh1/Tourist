package co.example.model.discount;

import java.util.List;

import lombok.Data;

/**
 * Created by Crish on 14.12.2017.
 */

@Data
public class Discount {
    private String parentImage;
    private List<DiscountCategory> discountCategories;
}
