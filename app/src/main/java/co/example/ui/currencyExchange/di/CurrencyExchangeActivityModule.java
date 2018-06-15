package co.example.ui.currencyExchange.di;

import dagger.Binds;
import dagger.Module;
import co.example.config.scope.PerActivity;
import co.example.core.BaseActivityModule;
import co.example.ui.currencyExchange.CurrencyExchangeActivity;
import co.example.ui.currencyExchange.CurrencyExchangePresenter;
import co.example.ui.currencyExchange.CurrencyExchangePresenterImpl;
import co.example.ui.currencyExchange.CurrencyExchangeView;

/**
 * Created by Crish on 14.12.2017.
 */

@Module(includes = BaseActivityModule.class)
public abstract class CurrencyExchangeActivityModule {
    @Binds
    @PerActivity
    abstract CurrencyExchangeView provideCurrencyExchangeView(CurrencyExchangeActivity activity);

    @Binds
    @PerActivity
    abstract CurrencyExchangePresenter provideCurrencyExchangePresenter(CurrencyExchangePresenterImpl presenter);
}
