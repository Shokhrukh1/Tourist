package co.example.ui.discount;

import co.example.core.BaseView;
import co.example.model.discount.Discount;

/**
 * Created by Crish on 14.12.2017.
 */

public interface DiscountView extends BaseView {
    void showDiscounts(Discount discount);
}
