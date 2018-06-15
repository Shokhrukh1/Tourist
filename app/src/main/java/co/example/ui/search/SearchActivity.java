package co.example.ui.search;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxAdapterView;
import com.jakewharton.rxbinding2.widget.RxTextSwitcher;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.functions.Consumer;
import co.example.R;
import co.example.core.BaseActivity;
import co.example.model.search.Category;
import co.example.model.search.Region;
import co.example.ui.companyList.CompanyListActivity;
import co.example.ui.search.adapters.CategoryAdapter;
import co.example.ui.search.adapters.RegionAdapter;
import co.example.utils.PreferencesHelper;
import co.example.utils.UIUtils;
import co.example.utils.Utils;

public class SearchActivity extends BaseActivity implements SearchView {
    @Inject
    SearchPresenter presenter;
    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.spCategory)
    Spinner spCategory;
    @BindView(R.id.spSubCategory)
    Spinner spSubCategory;
    @BindView(R.id.spRegion)
    Spinner spRegion;
    @BindView(R.id.spDistrict)
    Spinner spDistrict;
    @BindView(R.id.btnSearch)
    Button btnSearch;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        toolbar.setTitle(R.string.search);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        RxAdapterView.itemSelections(spCategory).subscribe(position -> {
            presenter.getSubCategory(position);
        });

        RxAdapterView.itemSelections(spRegion).subscribe(position -> {
            presenter.getDistricts(position);
        });

        RxView.clicks(btnSearch).subscribe(o -> {
            UIUtils.closeKeyboard(this, btnSearch);

            presenter.search(etSearch.getText().toString(),
                    spCategory.getSelectedItemPosition(),
                    spSubCategory.getSelectedItemPosition(),
                    spRegion.getSelectedItemPosition(),
                    spDistrict.getSelectedItemPosition());
        });

        etSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                UIUtils.closeKeyboard(this, btnSearch);

                presenter.search(etSearch.getText().toString(),
                        spCategory.getSelectedItemPosition(),
                        spSubCategory.getSelectedItemPosition(),
                        spRegion.getSelectedItemPosition(),
                        spDistrict.getSelectedItemPosition());

                return true;
            }

            return false;
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void showSearchData(List<Category> categories, List<Category> subCategory, List<Region> regions, List<Region> districts) {
        spCategory.setAdapter(new CategoryAdapter(this, 0, categories));
        spSubCategory.setAdapter(new CategoryAdapter(this, 0, subCategory));
        spRegion.setAdapter(new RegionAdapter(this, 0, regions));
        spDistrict.setAdapter(new RegionAdapter(this, 0, districts));
    }

    @Override
    public void showDistrictSpinner() {
        spDistrict.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideDistrictSpinner() {
        spDistrict.setVisibility(View.INVISIBLE);
        spDistrict.setSelection(0);
    }

    @Override
    public void changeDistricts(List<Region> districts) {
        ((RegionAdapter) spDistrict.getAdapter()).setRegions(districts);
    }

    @Override
    public void changeSubCategory(List<Category> subCategory) {
        ((CategoryAdapter) spSubCategory.getAdapter()).setCategories(subCategory);
    }

    @Override
    public void openCompanyListActivity(Intent intent) {
        intent.setClass(this, CompanyListActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void getData() {
        if (Utils.isNetworkAvailable(this)) {
            presenter.getSearchData();
        } else {
            showNoNetwork();
        }
    }
}
