package co.example.ui.insurance.di;

import dagger.Binds;
import dagger.Module;
import co.example.config.scope.PerActivity;
import co.example.core.BaseActivityModule;
import co.example.ui.insurance.InsuranceActivity;
import co.example.ui.insurance.InsurancePresenter;
import co.example.ui.insurance.InsurancePresenterImpl;
import co.example.ui.insurance.InsuranceView;

/**
 * Created by Crish on 20.12.2017.
 */

@Module(includes = BaseActivityModule.class)
public abstract class InsuranceActivityModule {
    @Binds
    @PerActivity
    abstract InsuranceView provideInsuranceView(InsuranceActivity activity);

    @Binds
    @PerActivity
    abstract InsurancePresenter provideInsurancePresenter(InsurancePresenterImpl presenter);
}
