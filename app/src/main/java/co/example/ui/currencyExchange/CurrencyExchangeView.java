package co.example.ui.currencyExchange;

import java.util.List;

import co.example.core.BaseView;
import co.example.model.Currency;

/**
 * Created by Crish on 14.12.2017.
 */

public interface CurrencyExchangeView extends BaseView {
    void showExchangeRates(List<Currency> exchangeRates);
}
