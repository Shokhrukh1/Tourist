package co.example.model;

import lombok.Data;

/**
 * Created by Crish on 28.12.2017.
 */

@Data
public class Language {
    private int languageId;
    private String title;
    private String abbr;
    private String flag;
    private int order;
    private String icon;
}
