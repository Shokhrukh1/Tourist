package co.example.ui.activateDiscountCard;

import co.example.core.Presenter;

/**
 * Created by Crish on 20.12.2017.
 */

public interface ActivateDiscountCardPresenter extends Presenter {
    void activateDiscountCard(String serialNumber, String activationCode);
}
