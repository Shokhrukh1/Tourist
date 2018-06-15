package co.example.ui.category;

import co.example.core.Presenter;

/**
 * Created by Crish on 29.12.2017.
 */

public interface CategoryPresenter extends Presenter {
    void getCategories(long parent);
}
