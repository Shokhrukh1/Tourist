package co.example.ui.category;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import co.example.BuildConfig;
import co.example.R;
import co.example.core.BaseActivity;
import co.example.core.ClickableBaseAdapter;
import co.example.model.category.Category;
import co.example.model.category.SubCategory;
import co.example.ui.category.adapters.CategoryAdapter;
import co.example.ui.companyList.CompanyListActivity;
import co.example.listeners.GlideRequestListener;
import co.example.utils.Utils;

import static co.example.ui.category.Constants.PARENT;
import static co.example.ui.category.Constants.TITLE;
import static co.example.ui.companyList.Constants.CATEGORY_ID;

public class CategoryActivity extends BaseActivity implements CategoryView, ClickableBaseAdapter.OnItemClickListener<SubCategory> {
    @Inject
    CategoryPresenter presenter;
    @BindView(R.id.rvCategories)
    RecyclerView rvCategories;
    @BindView(R.id.ivImage)
    ImageView ivImage;
    @BindView(R.id.pbLoading)
    ProgressBar pbLoading;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        toolbar.setTitle(getIntent().getStringExtra(TITLE));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_category;
    }

    @Override
    public void onItemClicked(int position) {

    }

    @Override
    public void onItemClicked(SubCategory item) {
        Intent intent = new Intent(this, CompanyListActivity.class);
        intent.putExtra(CATEGORY_ID, item.getCategoryId());
        intent.putExtra(co.example.ui.companyList.Constants.TITLE, item.getTitle());

        startActivity(intent);
    }

    @Override
    public void showCategories(Category category) {
        CategoryAdapter adapter = new CategoryAdapter(this, category.getSubCategories());
        adapter.setOnItemClickListener(this);

        rvCategories.setLayoutManager(new LinearLayoutManager(this));
        rvCategories.setAdapter(adapter);

        Glide.with(this)
                .load(BuildConfig.BASE_URL + category.getImage())
                .listener(new GlideRequestListener(ivImage, pbLoading))
                .into(ivImage);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void getData() {
        if (Utils.isNetworkAvailable(this)) {
            presenter.getCategories(getIntent().getLongExtra(PARENT, 0));
        } else {
            showNoNetwork();
        }
    }
}
