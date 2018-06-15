package co.example.ui.companyList;

import android.Manifest;
import android.location.Location;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import co.example.R;
import co.example.core.BaseActivity;
import co.example.model.Company;
import co.example.ui.companyList.adapters.CompanyAdapter;
import co.example.utils.PreferencesHelper;
import co.example.utils.Utils;

import static co.example.ui.companyList.Constants.CATEGORY_ID;
import static co.example.ui.companyList.Constants.DISTRICT_ID;
import static co.example.ui.companyList.Constants.REGION_ID;
import static co.example.ui.companyList.Constants.SEARCH_TEXT;
import static co.example.ui.companyList.Constants.TITLE;

public class CompanyListActivity extends BaseActivity implements CompanyListView, CompanyAdapter.OnLoadMoreListener {
    @Inject
    CompanyListPresenter presenter;
    @Inject
    RxPermissions rxPermissions;
    @Inject
    Observable<Location> location;
    @BindView(R.id.rvCompany)
    RecyclerView rvCompany;
    @BindView(R.id.pbLoadMore)
    ProgressBar pbLoadMore;
    @BindView(R.id.llFindLocation)
    LinearLayout llFindLocation;
    private Disposable locationDisposable;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        toolbar.setTitle(getIntent().getStringExtra(TITLE));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (Utils.isNetworkAvailable(this)) {
            llFindLocation.setVisibility(View.VISIBLE);
            rxPermissions.requestEach(Manifest.permission.ACCESS_FINE_LOCATION)
                    .subscribe(permission -> {
                        if (permission.granted) {
                            locationDisposable = location.subscribe(location -> {
                                llFindLocation.setVisibility(View.GONE);
                                presenter.getCompanies(
                                        getIntent().getStringExtra(SEARCH_TEXT),
                                        getIntent().getLongExtra(CATEGORY_ID, 0),
                                        getIntent().getLongExtra(REGION_ID, 0),
                                        getIntent().getLongExtra(DISTRICT_ID, 0),
                                        location.getLatitude(),
                                        location.getLongitude()
                                );
                            });
                        } else if (permission.shouldShowRequestPermissionRationale) {
                        } else {
                        }
                    });
        } else {
            showNoNetwork();
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_company_list;
    }

    @Override
    public void showCompanies(List<Company> companies) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvCompany.setLayoutManager(linearLayoutManager);
        rvCompany.setAdapter(new CompanyAdapter(this, rvCompany, this, companies, getString(R.string.distance), getString(R.string.km)));
    }

    @Override
    public void showMoreCompanies(List<Company> companies) {
        ((CompanyAdapter) rvCompany.getAdapter()).addItems(companies);
    }

    @Override
    protected void onStop() {
        if (locationDisposable != null) {
            locationDisposable.dispose();
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void getData() {
    }

    @Override
    public void onLoadMore() {
        locationDisposable = location.subscribe(location -> {
            presenter.getMoreCompanies(
                    getIntent().getStringExtra(SEARCH_TEXT),
                    getIntent().getLongExtra(CATEGORY_ID, 0),
                    getIntent().getLongExtra(REGION_ID, 0),
                    getIntent().getLongExtra(DISTRICT_ID, 0),
                    location.getLatitude(),
                    location.getLongitude()
            );
        });
    }

    @Override
    public void showLoadMoreProgressBar() {
        ((CompanyAdapter) rvCompany.getAdapter()).addItem(null);
    }

    @Override
    public void hideLoadMoreProgressBar() {
        ((CompanyAdapter) rvCompany.getAdapter())
                .removeLastItem();
    }

    @Override
    public void setLoaded() {
        ((CompanyAdapter) rvCompany.getAdapter())
                .setLoaded();
    }
}
