package co.example.ui.discount;

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
import co.example.model.discount.Discount;
import co.example.model.discount.DiscountCategory;
import co.example.ui.category.CategoryActivity;
import co.example.ui.discount.adapters.DiscountAdapter;
import co.example.listeners.GlideRequestListener;
import co.example.utils.Utils;

import static co.example.ui.category.Constants.PARENT;
import static co.example.ui.category.Constants.TITLE;

public class DiscountActivity extends BaseActivity implements DiscountView, ClickableBaseAdapter.OnItemClickListener<DiscountCategory> {
    @Inject
    DiscountPresenter presenter;
    @BindView(R.id.rvDiscounts)
    RecyclerView rvDiscounts;
    @BindView(R.id.ivImage)
    ImageView ivImage;
    @BindView(R.id.pbLoading)
    ProgressBar pbLoading;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        toolbar.setTitle(R.string.discounts);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_discounts;
    }

    @Override
    public void showDiscounts(Discount discount) {
        DiscountAdapter adapter = new DiscountAdapter(this, discount.getDiscountCategories());
        adapter.setOnItemClickListener(this);

        rvDiscounts.setLayoutManager(new LinearLayoutManager(this));
        rvDiscounts.setAdapter(adapter);

        Glide.with(this)
                .load(BuildConfig.BASE_URL + discount.getParentImage())
                .listener(new GlideRequestListener(ivImage, pbLoading))
                .into(ivImage);
    }

    @Override
    public void onItemClicked(int position) {

    }

    @Override
    public void onItemClicked(DiscountCategory item) {
        Intent intent = new Intent(this, CategoryActivity.class);
        intent.putExtra(TITLE, item.getTitle());
        intent.putExtra(PARENT, item.getCategoryId());

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
            presenter.getDiscounts();
        } else {
            showNoNetwork();
        }
    }
}
