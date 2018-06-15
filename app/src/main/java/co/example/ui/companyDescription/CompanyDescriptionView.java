package co.example.ui.companyDescription;

import java.util.List;

import co.example.core.BaseView;
import co.example.model.CompanyDescription;

/**
 * Created by Crish on 25.12.2017.
 */

public interface CompanyDescriptionView extends BaseView {
    void showCompanyDescription(CompanyDescription companyDescription);
    void showCallDialog(List<String> phones);
    void showAlertDialog();
    void showFavoriteLoading();
    void showWhiteFavorite();
    void showGoldFavorite();
    void showFavoriteAddedSuccessfully();
    void showFavoriteAddFailed();
    void showFavoriteRemovedSuccessfully();
    void showFavoriteRemoveFailed();
    void showPleaseSignIn();
    void prepareActivityResult(int resultCode, long saleId);
}
