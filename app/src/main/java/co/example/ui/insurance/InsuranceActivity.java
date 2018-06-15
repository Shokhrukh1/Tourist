package co.example.ui.insurance;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import co.example.R;
import co.example.core.BaseActivity;
import co.example.utils.PreferencesHelper;
import co.example.utils.Utils;

public class InsuranceActivity extends BaseActivity implements InsuranceView {
    @Inject
    InsurancePresenter presenter;
    @BindView(R.id.tvInsurance)
    TextView tvInsurance;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        toolbar.setTitle(R.string.insurance);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_insurance;
    }

    @Override
    public void showInsurance(String insurance) {
        tvInsurance.setText(insurance);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void getData() {
        if (Utils.isNetworkAvailable(this)) {
            presenter.getInsurance();
        } else {
            showNoNetwork();
        }
    }
}
