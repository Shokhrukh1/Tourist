package co.example.ui.regionDescription;

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

import static co.example.ui.regionDescription.Constants.ID;
import static co.example.ui.regionDescription.Constants.TITLE;

public class RegionDescriptionActivity extends BaseActivity implements RegionDescriptionView {
    @Inject
    RegionDescriptionPresenter presenter;
    @BindView(R.id.vpImages)
    ViewPager vpImages;
    @BindView(R.id.tvDescription)
    TextView tvDescription;
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

        RxView.clicks(tvSizeInc).subscribe(o -> {
            float currentTextSize = Utils.convertPxToDp(this, tvDescription.getTextSize());
            float defaultSize = Utils.convertPxToDp(this, getResources().getDimension(R.dimen.default_text_size));

            if (currentTextSize < defaultSize + 5) {
                tvDescription.setTextSize(TypedValue.COMPLEX_UNIT_SP, currentTextSize + 1);
            }
        });


        RxView.clicks(tvSizeDec).subscribe(o -> {
            float currentTextSize = Utils.convertPxToDp(this, tvDescription.getTextSize());
            float defaultSize = Utils.convertPxToDp(this, getResources().getDimension(R.dimen.default_text_size));

            if (currentTextSize > defaultSize) {
                tvDescription.setTextSize(TypedValue.COMPLEX_UNIT_SP, currentTextSize - 1);
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_region_description;
    }

    @Override
    public void showRegionDescription(Description description) {
        vpImages.setAdapter(new ImagePagerAdapter(this, description.getImages()));

        tvDescription.setText(description.getText());
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void getData() {
        if (Utils.isNetworkAvailable(this)) {
            presenter.getRegionDescription(getIntent().getLongExtra(ID, 0));
        } else {
            showNoNetwork();
        }
    }
}
