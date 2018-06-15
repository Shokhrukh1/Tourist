package co.example.ui.favorite.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import co.example.BuildConfig;
import co.example.R;
import co.example.core.BaseViewHolder;
import co.example.core.ClickableBaseAdapter;
import co.example.model.Favorite;
import co.example.listeners.GlideRequestListener;

/**
 * Created by crish on 1/11/18.
 */

public class FavoriteAdapter extends ClickableBaseAdapter<Favorite, FavoriteAdapter.FavoriteViewHolder> {
    private Context context;

    public FavoriteAdapter(Context context, List<Favorite> items) {
        super(items);
        this.context = context;
    }

    @Override
    public FavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FavoriteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_company, parent, false));
    }

    @Override
    public void onBindViewHolder(FavoriteViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        holder.tvTitle.setText(items.get(position).getTitle());

        Glide.with(context)
                .load(BuildConfig.BASE_URL + items.get(position).getImage())
                .listener(new GlideRequestListener(holder.ivLogo, holder.pbLoading))
                .into(holder.ivLogo);
    }

    @Override
    protected void onItemClicked(FavoriteViewHolder holder, int position) {
    }

    class FavoriteViewHolder extends BaseViewHolder {
        @BindView(R.id.ivLogo)
        ImageView ivLogo;
        @BindView(R.id.pbLoading)
        ProgressBar pbLoading;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvDistance)
        TextView tvDistance;

        public FavoriteViewHolder(View itemView) {
            super(itemView);

            tvDistance.setVisibility(View.GONE);
        }
    }
}
