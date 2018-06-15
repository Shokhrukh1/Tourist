package co.example.ui.favorite;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import co.example.R;
import co.example.core.BaseActivity;
import co.example.core.ClickableBaseAdapter;
import co.example.model.Favorite;
import co.example.ui.companyDescription.CompanyDescriptionActivity;
import co.example.ui.favorite.adapters.FavoriteAdapter;
import co.example.utils.Utils;

import static co.example.ui.companyDescription.Constants.SALE_ID;
import static co.example.ui.companyDescription.Constants.TITLE;
import static co.example.ui.favorite.Constants.REQUEST_CODE;

public class FavoriteActivity extends BaseActivity implements FavoriteView, ClickableBaseAdapter.OnItemClickListener<Favorite> {
    @Inject
    FavoritePresenter presenter;
    @BindView(R.id.rvFavorites)
    RecyclerView rvFavorites;
    @BindView(R.id.llNoFavorites)
    LinearLayout llNoFavorites;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        toolbar.setTitle(R.string.favorites);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_favorite;
    }

    @Override
    protected void getData() {
        if (Utils.isNetworkAvailable(this)) {
            presenter.getFavorites();
        } else {
            showNoNetwork();
        }
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showFavorites(List<Favorite> favorites) {
        FavoriteAdapter adapter = new FavoriteAdapter(this, favorites);
        adapter.setOnItemClickListener(this);

        rvFavorites.setLayoutManager(new LinearLayoutManager(this));
        rvFavorites.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(int position) {

    }

    @Override
    public void onItemClicked(Favorite item) {
        Intent intent = new Intent(this, CompanyDescriptionActivity.class);
        intent.putExtra(TITLE, item.getTitle());
        intent.putExtra(SALE_ID, item.getSaleId());

        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            presenter.removeFavorite(data.getLongExtra(Constants.SALE_ID, -1));
        }
    }

    @Override
    public void favoriteRemoved(int position) {
        rvFavorites.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showNoFavorites() {
        llNoFavorites.setVisibility(View.VISIBLE);
    }
}
