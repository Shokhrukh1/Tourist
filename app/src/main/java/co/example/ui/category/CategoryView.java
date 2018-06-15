package co.example.ui.category;

import co.example.core.BaseView;
import co.example.model.category.Category;

/**
 * Created by Crish on 29.12.2017.
 */

public interface CategoryView extends BaseView {
    void showCategories(Category category);
}
