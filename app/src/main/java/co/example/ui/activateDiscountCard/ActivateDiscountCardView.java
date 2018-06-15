package co.example.ui.activateDiscountCard;

import co.example.core.BaseView;

/**
 * Created by Crish on 20.12.2017.
 */

public interface ActivateDiscountCardView extends BaseView {
    void showActivateDiscountCardSuccessful();
    void showActivateDiscountCardFailed(String error);
    void clearViews();
}
