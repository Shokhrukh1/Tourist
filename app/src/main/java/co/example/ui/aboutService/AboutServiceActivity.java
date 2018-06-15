package co.example.ui.aboutService;

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

public class AboutServiceActivity extends BaseActivity implements AboutServiceView {
    @Inject
    AboutServicePresenter presenter;
    @BindView(R.id.tvAboutService)
    TextView tvAboutService;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        toolbar.setTitle(R.string.about_service);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_about_service;
    }

    @Override
    public void showAboutService(String aboutService) {
        tvAboutService.setText(aboutService);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void getData() {
        if (Utils.isNetworkAvailable(this)) {
            presenter.getAboutService();
        } else {
            showNoNetwork();
        }
    }
}
