package co.example.ui.supportService;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import butterknife.BindView;
import co.example.R;
import co.example.core.BaseActivity;
import co.example.utils.Utils;

public class SupportServiceActivity extends BaseActivity implements SupportServiceView {
    @Inject
    SupportServicePresenter presenter;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        toolbar.setTitle(R.string.support_service);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_support_service;
    }

    @Override
    protected void getData() {
        if (Utils.isNetworkAvailable(this)) {
        } else {
            showNoNetwork();
        }
    }
}
