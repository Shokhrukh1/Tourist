package co.example.ui.search;

import android.content.Intent;

import java.util.List;

import co.example.core.BaseView;
import co.example.model.search.Category;
import co.example.model.search.Region;

/**
 * Created by Crish on 27.12.2017.
 */

public interface SearchView extends BaseView {
    void showSearchData(List<Category> categories, List<Category> subCategory, List<Region> regions, List<Region> districts);
    void showDistrictSpinner();
    void hideDistrictSpinner();
    void changeDistricts(List<Region> districts);
    void changeSubCategory(List<Category> subCategory);
    void openCompanyListActivity(Intent intent);
}
