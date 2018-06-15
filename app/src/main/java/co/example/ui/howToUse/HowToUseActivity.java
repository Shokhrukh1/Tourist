package co.example.ui.howToUse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import co.example.R;
import co.example.core.BaseActivity;
import co.example.utils.PreferencesHelper;
import co.example.utils.Utils;

public class HowToUseActivity extends BaseActivity implements HowToUseView {
    @Inject
    HowToUsePresenter presenter;
    @BindView(R.id.tvHowToUse)
    TextView tvHowToUse;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        toolbar.setTitle(R.string.how_to_use);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_how_to_use;
    }

    @Override
    public void showHowToUse(String howToUse) {
        tvHowToUse.setText(howToUse);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void getData() {
        if (Utils.isNetworkAvailable(this)) {
            presenter.getHowToUse();
        } else {
            showNoNetwork();
        }
    }
}
