package co.example.ui.companyList;

import java.util.List;

import co.example.core.BaseView;
import co.example.model.Company;

/**
 * Created by Crish on 21.12.2017.
 */

public interface CompanyListView extends BaseView {
    void showCompanies(List<Company> companies);
    void showMoreCompanies(List<Company> companies);
    void showLoadMoreProgressBar();
    void hideLoadMoreProgressBar();
    void setLoaded();
}
