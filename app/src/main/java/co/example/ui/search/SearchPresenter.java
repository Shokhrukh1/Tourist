package co.example.ui.search;

import co.example.core.Presenter;

/**
 * Created by Crish on 27.12.2017.
 */

public interface SearchPresenter extends Presenter {
    void getSearchData();
    void getDistricts(int position);
    void getSubCategory(int position);
    void search(String searchText, int categoryPosition, int subCategoryPosition, int regionPosition, int districtPosition);
}
