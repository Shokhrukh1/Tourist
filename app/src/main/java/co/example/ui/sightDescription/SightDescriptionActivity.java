package co.example.ui.sightDescription;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;

import javax.inject.Inject;

import butterknife.BindView;
import co.example.R;
import co.example.core.BaseActivity;
import co.example.model.Description;
import co.example.adapters.ImagePagerAdapter;
import co.example.utils.Utils;

import static co.example.ui.sightDescription.Constants.DEFAULT_TEXT_SIZE;
import static co.example.ui.sightDescription.Constants.ID;
import static co.example.ui.sightDescription.Constants.TITLE;

public class SightDescriptionActivity extends BaseActivity implements SightDescriptionView {
    @Inject
    SightDescriptionPresenter presenter;
    @BindView(R.id.vpImages)
    ViewPager vpImages;
    @BindView(R.id.tvSightDescription)
    TextView tvSightDescription;
    @BindView(R.id.tvSizeInc)
    TextView tvSizeInc;
    @BindView(R.id.tvSizeDec)
    TextView tvSizeDec;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        toolbar.setTitle(getIntent().getStringExtra(TITLE));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvSightDescription.setTextSize(TypedValue.COMPLEX_UNIT_SP, DEFAULT_TEXT_SIZE);


        RxView.clicks(tvSizeInc).subscribe(o -> {
            float currentTextSize = Utils.convertPxToDp(this, tvSightDescription.getTextSize());
            float defaultSize = Utils.convertPxToDp(this, getResources().getDimension(R.dimen.default_text_size));

            if (currentTextSize < defaultSize + 5) {
                tvSightDescription.setTextSize(TypedValue.COMPLEX_UNIT_SP, currentTextSize + 1);
            }
        });


        RxView.clicks(tvSizeDec).subscribe(o -> {
            float currentTextSize = Utils.convertPxToDp(this, tvSightDescription.getTextSize());
            float defaultSize = Utils.convertPxToDp(this, getResources().getDimension(R.dimen.default_text_size));

            if (currentTextSize > defaultSize) {
                tvSightDescription.setTextSize(TypedValue.COMPLEX_UNIT_SP, currentTextSize - 1);
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_sight_description;
    }

    @Override
    public void showSightDescription(Description description) {
        tvSightDescription.setText(description.getText());

        vpImages.setAdapter(new ImagePagerAdapter(this, description.getImages()));
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void getData() {
        if (Utils.isNetworkAvailable(this)) {
            presenter.getSightDescription(getIntent().getLongExtra(ID, 0));
        } else {
            showNoNetwork();
        }
    }
}
