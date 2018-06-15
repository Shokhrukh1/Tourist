package co.example.ui.currencyExchange;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import co.example.core.BasePresenterImpl;
import co.example.model.Currency;
import co.example.network.ApiManager;

/**
 * Created by Crish on 14.12.2017.
 */

public class CurrencyExchangePresenterImpl extends BasePresenterImpl<CurrencyExchangeView> implements CurrencyExchangePresenter {
    private ApiManager apiManager;
    private Disposable disposable;

    @Inject
    public CurrencyExchangePresenterImpl(CurrencyExchangeView view, ApiManager apiManager) {
        super(view);
        this.apiManager = apiManager;
    }

    @Override
    public void getExchangeRates() {
        view.showProgressBar();

        disposable = apiManager.getExchangeRates()
                .subscribe(s -> {
                    try {
                        JSONObject jsonData = new JSONObject(s);
                        List<Currency> currencies = new ArrayList<>();

                        if (jsonData.isNull("currencies")) {
                            view.showServerError();
                            return;
                        }

                        JSONArray exchangeRates = jsonData.getJSONArray("currencies");

                        for (int i = 0; i < exchangeRates.length(); i++) {
                            JSONObject jsonObject = exchangeRates.getJSONObject(i);

                            Currency currency = new Currency();
                            currency.setTitle(jsonObject.getString("title"));
                            currency.setValue(jsonObject.getString("value"));
                            currency.setDate(jsonObject.getString("date"));

                            currencies.add(currency);
                        }

                        view.showExchangeRates(currencies);
                        view.showData();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, throwable -> {

                });
    }

    @Override
    public void onDestroy() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        super.onDestroy();
    }
}
