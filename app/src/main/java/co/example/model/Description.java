package co.example.model;

import java.util.List;

import lombok.Data;

/**
 * Created by Crish on 18.12.2017.
 */

@Data
public class Description {
    private String title;
    private String text;
    private List<String> images;
}
