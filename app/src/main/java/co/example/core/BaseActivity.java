package co.example.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.jakewharton.rxbinding2.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import io.reactivex.functions.Consumer;
import co.example.R;
import co.example.ui.dialogs.LoadingDialog;

/**
 * Created by Portable-Acer on 05.12.2017.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @Nullable
    @BindView(R.id.flProgressBar)
    protected FrameLayout flProgressBar;
    @Nullable
    @BindView(R.id.llNoData)
    protected LinearLayout llNoData;
    @Nullable
    @BindView(R.id.llNoNetwork)
    protected LinearLayout llNoNetwork;
    @Nullable
    @BindView(R.id.llServerError)
    protected LinearLayout llServerError;
    @Nullable
    @BindView(R.id.btnRetry)
    Button btnRetry;
    @Nullable
    @BindView(R.id.llContent)
    LinearLayout llContent;

    private Unbinder unbinder;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        unbinder = ButterKnife.bind(this);

        init(savedInstanceState);

        getData();

        RxView.clicks(btnRetry).subscribe(o -> {
            getData();
        });
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    protected abstract void init(@Nullable Bundle savedInstanceState);

    protected abstract int getLayout();

    protected abstract void getData();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return true;
    }

    public void showProgressBar() {
        flProgressBar.setVisibility(View.VISIBLE);
        llNoData.setVisibility(View.GONE);
        llNoNetwork.setVisibility(View.GONE);
        llServerError.setVisibility(View.GONE);
    }

    public void showNoNetwork() {
        flProgressBar.setVisibility(View.GONE);
        llNoData.setVisibility(View.GONE);
        llNoNetwork.setVisibility(View.VISIBLE);
        llServerError.setVisibility(View.GONE);
    }

    public void showNoData() {
        flProgressBar.setVisibility(View.GONE);
        llNoData.setVisibility(View.VISIBLE);
        llNoNetwork.setVisibility(View.GONE);
        llServerError.setVisibility(View.GONE);
    }

    public void showData() {
        flProgressBar.setVisibility(View.GONE);
        llNoData.setVisibility(View.GONE);
        llNoNetwork.setVisibility(View.GONE);
        llServerError.setVisibility(View.GONE);
    }

    public void showServerError() {
        flProgressBar.setVisibility(View.GONE);
        llNoData.setVisibility(View.GONE);
        llNoNetwork.setVisibility(View.GONE);
        llServerError.setVisibility(View.VISIBLE);
    }

    public void showLoadingDialog() {
        loadingDialog = new LoadingDialog();
        loadingDialog.show(getSupportFragmentManager(), "LoadingDialog");
    }

    public void hideLoadingDialog() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    public void clearFocus() {
        llContent.clearFocus();
    }
}
