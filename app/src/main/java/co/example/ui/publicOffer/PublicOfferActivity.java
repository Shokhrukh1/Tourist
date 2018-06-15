package co.example.ui.publicOffer;

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

public class PublicOfferActivity extends BaseActivity implements PublicOfferView {
    @Inject
    PublicOfferPresenter presenter;
    @BindView(R.id.tvPublicOffer)
    TextView tvPublicOffer;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        toolbar.setTitle(R.string.public_offer);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_public_offer;
    }

    @Override
    public void showPublicOffer(String publicOffer) {
        tvPublicOffer.setText(publicOffer);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void getData() {
        if (Utils.isNetworkAvailable(this)) {
            presenter.getPublicOffer();
        } else {
            showNoNetwork();
        }
    }
}
