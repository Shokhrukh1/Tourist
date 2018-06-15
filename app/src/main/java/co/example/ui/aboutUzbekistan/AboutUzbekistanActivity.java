package co.example.ui.aboutUzbekistan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import co.example.R;
import co.example.core.BaseActivity;
import co.example.model.AboutUzbekistan;
import co.example.ui.aboutUzbekistan.adapters.AboutUzbekistanAdapter;
import co.example.utils.PreferencesHelper;
import co.example.utils.Utils;

public class AboutUzbekistanActivity extends BaseActivity implements AboutUzbekistanView {
    @Inject
    AboutUzbekistanPresenter presenter;
    @BindView(R.id.rvAboutUzbekistan)
    RecyclerView rvAboutUzbekistan;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        toolbar.setTitle(R.string.about_uzbekistan);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_about_uzbekistan;
    }

    @Override
    public void showAboutUzbekistan(List<AboutUzbekistan> aboutUzbekistanList) {
        rvAboutUzbekistan.setLayoutManager(new LinearLayoutManager(this));
        rvAboutUzbekistan.setAdapter(new AboutUzbekistanAdapter(aboutUzbekistanList));
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void getData() {
        if (Utils.isNetworkAvailable(this)) {
            presenter.getAboutUzbekistan();
        } else {
            showNoNetwork();
        }
    }
}
