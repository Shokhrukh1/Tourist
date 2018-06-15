package co.example.ui.historicalPlaces.adapters;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import butterknife.BindView;
import co.example.BuildConfig;
import co.example.R;
import co.example.core.BaseViewHolder;
import co.example.core.ClickableBaseAdapter;
import co.example.model.Region;

/**
 * Created by Crish on 11.12.2017.
 */

public class RegionAdapter extends ClickableBaseAdapter<Region, RegionAdapter.RegionViewHolder> {

    public RegionAdapter(List<Region> items) {
        super(items);
    }

    @Override
    public RegionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_region, parent, false);

        return new RegionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RegionViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Region region = items.get(position);

        holder.tvName.setVisibility(View.INVISIBLE);
        holder.ivRegion.setVisibility(View.INVISIBLE);
        holder.pbLoading.setVisibility(View.VISIBLE);
        holder.tvName.setText(region.getName());

        Glide.with(holder.itemView.getContext())
                .load(BuildConfig.BASE_URL + region.getImageUrl())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.pbLoading.setVisibility(View.INVISIBLE);
                        holder.ivRegion.setVisibility(View.VISIBLE);
                        holder.tvName.setVisibility(View.VISIBLE);
                        return false;
                    }
                })
                .into(holder.ivRegion);
    }

    @Override
    protected void onItemClicked(RegionViewHolder holder, int position) {

    }

    class RegionViewHolder extends BaseViewHolder {
        @BindView(R.id.tvTitle)
        TextView tvName;
        @BindView(R.id.ivRegion)
        ImageView ivRegion;
        @BindView(R.id.pbLoading)
        ProgressBar pbLoading;

        public RegionViewHolder(View itemView) {
            super(itemView);
        }
    }
}
