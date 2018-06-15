package co.example.ui.currencyExchange;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import co.example.R;
import co.example.core.BaseActivity;
import co.example.model.Currency;
import co.example.ui.currencyExchange.adapters.CurrencyAdapter;
import co.example.utils.Utils;

public class CurrencyExchangeActivity extends BaseActivity implements CurrencyExchangeView {
    @Inject
    CurrencyExchangePresenter presenter;
    @BindView(R.id.rvExchangeRates)
    RecyclerView rvExchangeRates;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        toolbar.setTitle(R.string.currency_exchange);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_currency_exchange;
    }

    @Override
    public void showExchangeRates(List<Currency> exchangeRates) {
        rvExchangeRates.setLayoutManager(new LinearLayoutManager(this));
        rvExchangeRates.setAdapter(new CurrencyAdapter(this, exchangeRates));
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void getData() {
        if (Utils.isNetworkAvailable(this)) {
            presenter.getExchangeRates();
        } else {
            showNoNetwork();
        }
    }
}
