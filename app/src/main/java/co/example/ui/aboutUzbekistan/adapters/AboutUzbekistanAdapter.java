package co.example.ui.aboutUzbekistan.adapters;

import android.content.Intent;
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
import com.jakewharton.rxbinding2.view.RxView;

import java.util.List;

import butterknife.BindView;
import co.example.BuildConfig;
import co.example.R;
import co.example.core.BaseAdapter;
import co.example.core.BaseViewHolder;
import co.example.model.AboutUzbekistan;
import co.example.listeners.GlideRequestListener;
import co.example.ui.sightDescription.SightDescriptionActivity;

import static co.example.ui.sightDescription.Constants.ID;
import static co.example.ui.sightDescription.Constants.TITLE;

/**
 * Created by Crish on 11.12.2017.
 */

public class AboutUzbekistanAdapter extends BaseAdapter<AboutUzbekistan, AboutUzbekistanAdapter.AboutUzbekistanViewHolder> {
    public AboutUzbekistanAdapter(List<AboutUzbekistan> items) {
        super(items);
    }

    @Override
    public AboutUzbekistanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_region, parent, false);

        return new AboutUzbekistanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AboutUzbekistanViewHolder holder, int position) {
        AboutUzbekistan aboutUzbekistan = items.get(position);

        holder.tvName.setVisibility(View.INVISIBLE);
        holder.ivRegion.setVisibility(View.INVISIBLE);
        holder.pbLoading.setVisibility(View.VISIBLE);
        holder.tvName.setText(aboutUzbekistan.getTitle());

        Glide.with(holder.itemView.getContext())
                .load(BuildConfig.BASE_URL + aboutUzbekistan.getImageUrl())
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

    class AboutUzbekistanViewHolder extends BaseViewHolder {
        @BindView(R.id.ivRegion)
        ImageView ivRegion;
        @BindView(R.id.tvTitle)
        TextView tvName;
        @BindView(R.id.pbLoading)
        ProgressBar pbLoading;

        public AboutUzbekistanViewHolder(View itemView) {
            super(itemView);

            RxView.clicks(itemView).subscribe(o -> {
                Intent intent = new Intent(itemView.getContext(), SightDescriptionActivity.class);
                intent.putExtra(TITLE, items.get(getAdapterPosition()).getTitle());
                intent.putExtra(ID, items.get(getAdapterPosition()).getPageId());

                itemView.getContext().startActivity(intent);
            });
        }
    }
}
