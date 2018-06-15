package co.example.model.category;

import java.util.List;

import lombok.Data;

/**
 * Created by Crish on 29.12.2017.
 */

@Data
public class Category {
    private String image;
    private List<SubCategory> subCategories;
}
