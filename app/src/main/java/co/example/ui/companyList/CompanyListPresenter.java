package co.example.ui.companyList;

import co.example.core.Presenter;

/**
 * Created by Crish on 21.12.2017.
 */

public interface CompanyListPresenter extends Presenter {
    void getCompanies(String searchText, Long categoryId, Long regionId, Long districtId, double latitude, double longitude);
    void getMoreCompanies(String searchText, Long categoryId, Long regionId, Long districtId, double latitude, double longitude);
}
