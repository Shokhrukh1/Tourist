package co.example.ui.category.adapters;

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
import co.example.model.category.SubCategory;
import co.example.listeners.GlideRequestListener;

/**
 * Created by Crish on 29.12.2017.
 */

public class CategoryAdapter extends ClickableBaseAdapter<SubCategory, CategoryAdapter.CategoryViewHolder> {
    private Context context;

    public CategoryAdapter(Context context, List<SubCategory> items) {
        super(items);
        this.context = context;
    }

    @Override
    protected void onItemClicked(CategoryViewHolder holder, int position) {

    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_with_icon, parent, false);

        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        holder.ivIcon.setVisibility(View.INVISIBLE);
        holder.pbLoading.setVisibility(View.VISIBLE);
        holder.tvTitle.setText(items.get(position).getTitle());

        Glide.with(context)
                .load(BuildConfig.BASE_URL + items.get(position).getXxxhdpi())
                .listener(new GlideRequestListener(holder.ivIcon, holder.pbLoading))
                .into(holder.ivIcon);
    }

    class CategoryViewHolder extends BaseViewHolder {
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.ivIcon)
        ImageView ivIcon;
        @BindView(R.id.pbLoading)
        ProgressBar pbLoading;

        public CategoryViewHolder(View itemView) {
            super(itemView);
        }
    }
}
