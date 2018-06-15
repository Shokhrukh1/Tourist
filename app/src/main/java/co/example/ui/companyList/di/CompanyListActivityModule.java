package co.example.ui.companyList.di;

import android.support.v7.app.AppCompatActivity;

import dagger.Binds;
import dagger.Module;
import co.example.config.scope.PerActivity;
import co.example.core.BaseActivityModule;
import co.example.ui.companyList.CompanyListActivity;
import co.example.ui.companyList.CompanyListPresenter;
import co.example.ui.companyList.CompanyListPresenterImpl;
import co.example.ui.companyList.CompanyListView;

/**
 * Created by Crish on 21.12.2017.
 */

@Module(includes = BaseActivityModule.class)
public abstract class CompanyListActivityModule {
    @Binds
    @PerActivity
    abstract AppCompatActivity provideCompanyListActivity(CompanyListActivity activity);

    @Binds
    @PerActivity
    abstract CompanyListView provideCompanyListView(CompanyListActivity activity);

    @Binds
    @PerActivity
    abstract CompanyListPresenter provideCompanyListPresenter(CompanyListPresenterImpl presenter);
}
