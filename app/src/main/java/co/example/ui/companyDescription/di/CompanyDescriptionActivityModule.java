package co.example.ui.companyDescription.di;

import android.support.v7.app.AppCompatActivity;

import dagger.Binds;
import dagger.Module;
import co.example.config.scope.PerActivity;
import co.example.core.BaseActivityModule;
import co.example.ui.companyDescription.CompanyDescriptionActivity;
import co.example.ui.companyDescription.CompanyDescriptionPresenter;
import co.example.ui.companyDescription.CompanyDescriptionPresenterImpl;
import co.example.ui.companyDescription.CompanyDescriptionView;

/**
 * Created by Crish on 25.12.2017.
 */

@Module(includes = BaseActivityModule.class)
public abstract class CompanyDescriptionActivityModule {
    @Binds
    @PerActivity
    abstract AppCompatActivity provideCompanyDescriptionActivity(CompanyDescriptionActivity activity);

    @Binds
    @PerActivity
    abstract CompanyDescriptionView provideCompanyDescriptionView(CompanyDescriptionActivity activity);

    @Binds
    @PerActivity
    abstract CompanyDescriptionPresenter provideCompanyDescriptionPresenter(CompanyDescriptionPresenterImpl presenter);
}
