package co.example.ui.companyDescription;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import co.example.R;
import co.example.core.BaseActivity;
import co.example.model.CompanyDescription;
import co.example.adapters.ImagePagerAdapter;
import co.example.ui.companyDescription.dialogs.CallDialog;
import co.example.utils.UIUtils;
import co.example.utils.Utils;

import static co.example.ui.companyDescription.Constants.SALE_ID;
import static co.example.ui.companyDescription.Constants.TITLE;

public class CompanyDescriptionActivity extends BaseActivity implements CompanyDescriptionView {
    @Inject
    CompanyDescriptionPresenter presenter;
    @Inject
    RxPermissions rxPermissions;
    @BindView(R.id.vpImages)
    ViewPager vpImages;
    @BindView(R.id.tvCategory)
    TextView tvCategory;
    @BindView(R.id.tvRegion)
    TextView tvRegion;
    @BindView(R.id.tvDistrict)
    TextView tvDistrict;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.tvLandmark)
    TextView tvLandmark;
    @BindView(R.id.tvAboutCompany)
    TextView tvAboutCompany;
    @BindView(R.id.btnBook)
    Button btnBook;
    @BindView(R.id.btnHowToGetThere)
    Button btnHowToGetThere;
    @BindView(R.id.elBasicData)
    ExpandableLayout elBasicData;
    @BindView(R.id.elAboutCompany)
    ExpandableLayout elAboutCompany;
    @BindView(R.id.rlHeaderBasicData)
    RelativeLayout rlHeaderBasicData;
    @BindView(R.id.rlHeaderAboutCompany)
    RelativeLayout rlHeaderAboutCompany;
    @BindView(R.id.ivArrowUpBasicData)
    ImageView ivArrowUpBasicData;
    @BindView(R.id.ivArrowUpAboutCompany)
    ImageView ivArrowUpAboutCompany;
    @BindView(R.id.tvSizeInc)
    TextView tvSizeInc;
    @BindView(R.id.tvSizeDec)
    TextView tvSizeDec;
    @BindView(R.id.flFavoriteLoading)
    FrameLayout flFavoriteLoading;
    @BindView(R.id.flFavoriteImage)
    FrameLayout flFavoriteImage;
    @BindView(R.id.ivFavorite)
    ImageView ivFavorite;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        toolbar.setTitle(getIntent().getStringExtra(TITLE));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        elBasicData.setOnExpansionUpdateListener((expansionFraction, state) -> {
            if (ivArrowUpBasicData != null) {
                ivArrowUpBasicData.setRotation(expansionFraction * 180);
            }
        });

        elAboutCompany.setOnExpansionUpdateListener((expansionFraction, state) -> {
            if (ivArrowUpAboutCompany != null) {
                ivArrowUpAboutCompany.setRotation(expansionFraction * 180);
            }
        });

        RxView.clicks(btnBook).subscribe(o -> {
            rxPermissions.requestEach(Manifest.permission.CALL_PHONE)
                    .subscribe(permission -> {
                        if (permission.granted) {
                            presenter.getPhones();
                        } else if (permission.shouldShowRequestPermissionRationale) {

                        } else {

                        }
                    });
        });

        RxView.clicks(btnHowToGetThere).subscribe(o -> {
            UIUtils.showAlertDialog(this, R.string.feature_is_not_available, R.string.ok, (dialogInterface, i) -> {
                dialogInterface.dismiss();
            });
        });

        RxView.clicks(rlHeaderBasicData).subscribe(o -> {
            if (elBasicData.isExpanded()) {
                elBasicData.collapse();
            } else {
                elBasicData.expand();
            }
        });

        RxView.clicks(rlHeaderAboutCompany).subscribe(o -> {
            if (elAboutCompany.isExpanded()) {
                elAboutCompany.collapse();
            } else {
                elAboutCompany.expand();
            }
        });

        RxView.clicks(tvSizeInc).subscribe(o -> {
            float currentTextSize = Utils.convertPxToDp(this, tvAboutCompany.getTextSize());
            float defaultSize = Utils.convertPxToDp(this, getResources().getDimension(R.dimen.default_text_size));

            if (currentTextSize < defaultSize + 5) {
                tvAboutCompany.setTextSize(TypedValue.COMPLEX_UNIT_SP, currentTextSize + 1);
            }
        });


        RxView.clicks(tvSizeDec).subscribe(o -> {
            float currentTextSize = Utils.convertPxToDp(this, tvAboutCompany.getTextSize());
            float defaultSize = Utils.convertPxToDp(this, getResources().getDimension(R.dimen.default_text_size));

            if (currentTextSize > defaultSize) {
                tvAboutCompany.setTextSize(TypedValue.COMPLEX_UNIT_SP, currentTextSize - 1);
            }
        });

        RxView.clicks(flFavoriteImage).subscribe(o -> {
            if (presenter.isAddedFavorite()) {
                presenter.removeFavorite(getIntent().getLongExtra(SALE_ID, 0));
            } else {
                presenter.addFavorite(getIntent().getLongExtra(SALE_ID, 0));
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_company_description;
    }

    @Override
    public void showCompanyDescription(CompanyDescription companyDescription) {

        if (!companyDescription.getImages().isEmpty()) {
            vpImages.setAdapter(new ImagePagerAdapter(this, companyDescription.getImages()));
        }

        tvCategory.setText(companyDescription.getCategoryTitle());
        tvRegion.setText(companyDescription.getRegionTitle());
        tvDistrict.setText(companyDescription.getDistrictTitle());
        tvAddress.setText(companyDescription.getAddress());
        tvLandmark.setText(companyDescription.getLandmark());
        tvAboutCompany.setText(companyDescription.getText());
    }

    @Override
    public void showCallDialog(List<String> phones) {
        CallDialog.newInstance(phones)
                .show(getSupportFragmentManager(), "CallDialog");
    }

    @Override
    public void showAlertDialog() {
        UIUtils.showAlertDialog(this, R.string.no_number, R.string.ok, (dialogInterface, i) -> {
            dialogInterface.dismiss();
        });
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void getData() {
        if (Utils.isNetworkAvailable(this)) {
            presenter.getCompanyDescription(getIntent().getLongExtra(SALE_ID, 0));

            presenter.checkAddedFavorite(getIntent().getLongExtra(SALE_ID, 0));
        } else {
            showNoNetwork();
        }
    }

    @Override
    public void showFavoriteLoading() {
        flFavoriteImage.setVisibility(View.GONE);
        flFavoriteLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void showWhiteFavorite() {
        flFavoriteLoading.setVisibility(View.GONE);
        flFavoriteImage.setVisibility(View.VISIBLE);
        ivFavorite.setColorFilter(ContextCompat.getColor(this, android.R.color.white));
    }

    @Override
    public void showGoldFavorite() {
        flFavoriteLoading.setVisibility(View.GONE);
        flFavoriteImage.setVisibility(View.VISIBLE);
        ivFavorite.setColorFilter(ContextCompat.getColor(this, R.color.colorGold));
    }

    @Override
    public void showFavoriteAddedSuccessfully() {
        Toast.makeText(this, R.string.added_successfully, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showFavoriteAddFailed() {
        Toast.makeText(this, R.string.add_failed, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showFavoriteRemovedSuccessfully() {
        Toast.makeText(this, R.string.removed_successfully, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showFavoriteRemoveFailed() {
        Toast.makeText(this, R.string.remove_failed, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showPleaseSignIn() {
        Toast.makeText(this, R.string.please_sign_in, Toast.LENGTH_LONG).show();
    }

    @Override
    public void prepareActivityResult(int resultCode, long saleId) {
        Intent intent = new Intent();
        intent.putExtra(co.example.ui.favorite.Constants.SALE_ID, saleId);

        setResult(resultCode, intent);
    }
}
