package co.example.ui.historicalPlaces;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import co.example.R;
import co.example.core.BaseActivity;
import co.example.core.ClickableBaseAdapter;
import co.example.model.Region;
import co.example.ui.historicalPlaces.adapters.RegionAdapter;
import co.example.ui.regionDescription.RegionDescriptionActivity;
import co.example.utils.PreferencesHelper;
import co.example.utils.Utils;

import static co.example.ui.regionDescription.Constants.ID;
import static co.example.ui.regionDescription.Constants.TITLE;

public class HistoricalPlacesActivity extends BaseActivity implements HistoricalPlacesView, ClickableBaseAdapter.OnItemClickListener<Region> {
    @Inject
    HistoricalPlacesPresenter presenter;
    @BindView(R.id.rvRegions)
    RecyclerView rvRegions;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        toolbar.setTitle(R.string.historical_places);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_historical_places;
    }

    @Override
    public void showHistoricalPlaces(List<Region> regions) {
        RegionAdapter adapter = new RegionAdapter(regions);
        adapter.setOnItemClickListener(this);

        rvRegions.setLayoutManager(new LinearLayoutManager(this));
        rvRegions.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void getData() {
        if (Utils.isNetworkAvailable(this)) {
            presenter.getHistoricalPlaces();
        } else {
            showNoNetwork();
        }
    }

    @Override
    public void onItemClicked(int position) {

    }

    @Override
    public void onItemClicked(Region item) {
        Intent intent = new Intent(this, RegionDescriptionActivity.class);
        intent.putExtra(TITLE, item.getName());
        intent.putExtra(ID, item.getRegionId());

        startActivity(intent);
    }
}
