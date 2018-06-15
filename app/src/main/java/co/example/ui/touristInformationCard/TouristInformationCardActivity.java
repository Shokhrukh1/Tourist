package co.example.ui.touristInformationCard;

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

public class TouristInformationCardActivity extends BaseActivity implements TouristInformationCardView {
    @Inject
    TouristInformationCardPresenter presenter;
    @BindView(R.id.tvTouristInformationCard)
    TextView tvTouristInformationCard;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        toolbar.setTitle(R.string.tourist_information_card);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_tourist_information_card;
    }

    @Override
    public void showTouristInformationCard(String touristInformationCard) {
        tvTouristInformationCard.setText(touristInformationCard);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void getData() {
        if (Utils.isNetworkAvailable(this)) {
            presenter.getTouristInformationCard();
        } else {
            showNoNetwork();
        }
    }
}
