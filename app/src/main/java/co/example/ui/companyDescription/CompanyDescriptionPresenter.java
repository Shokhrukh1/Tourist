package co.example.ui.companyDescription;

import co.example.core.Presenter;

/**
 * Created by Crish on 25.12.2017.
 */

public interface CompanyDescriptionPresenter extends Presenter {
    void getCompanyDescription(long saleId);
    void getPhones();
    void checkAddedFavorite(long saleId);
    void addFavorite(long saleId);
    void removeFavorite(long saleId);
    boolean isAddedFavorite();
}
