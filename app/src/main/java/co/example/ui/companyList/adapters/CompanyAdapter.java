package co.example.ui.companyList.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.List;

import butterknife.BindView;
import co.example.BuildConfig;
import co.example.R;
import co.example.core.BaseAdapter;
import co.example.core.BaseViewHolder;
import co.example.model.Company;
import co.example.ui.companyDescription.CompanyDescriptionActivity;
import co.example.listeners.GlideRequestListener;

import static co.example.ui.companyDescription.Constants.SALE_ID;
import static co.example.ui.companyDescription.Constants.TITLE;

/**
 * Created by Crish on 22.12.2017.
 */

public class CompanyAdapter extends BaseAdapter<Company, BaseViewHolder> {
    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public static final int VIEW_TYPE_ITEM = 0;
    public static final int VIEW_TYPE_LOADING = 1;

    private Context context;
    private String unit;
    private String distance;
    private OnLoadMoreListener onLoadMoreListener;
    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem;
    private int totalItemCount;

    public CompanyAdapter(Context context, RecyclerView recyclerView, OnLoadMoreListener onLoadMoreListener, List<Company> items, String distance, String unit) {
        super(items);
        this.context = context;
        this.unit = unit;
        this.distance = distance;
        this.onLoadMoreListener = onLoadMoreListener;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }

                    isLoading = true;
                }
            }
        });
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void setLoaded() {
        isLoading = false;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_company, parent, false);

            return new CompanyViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_progress_bar, parent, false);

            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (holder.getItemViewType() == VIEW_TYPE_ITEM) {
            CompanyViewHolder view = (CompanyViewHolder) holder;

            view.ivLogo.setVisibility(View.INVISIBLE);
            view.pbLoading.setVisibility(View.VISIBLE);
            view.tvDistance.setText(String.format("%s %s %s", distance, items.get(position).getDistance(), unit));
            view.tvName.setText(items.get(position).getTitle());

            Glide.with(context)
                    .load(BuildConfig.BASE_URL + items.get(position).getImage())
                    .listener(new GlideRequestListener(view.ivLogo, view.pbLoading))
                    .into(view.ivLogo);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public void addItem(Company item) {
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    public void removeLastItem() {
        items.remove(items.size() - 1);
        notifyItemRemoved(items.size());
    }

    class CompanyViewHolder extends BaseViewHolder {
        @BindView(R.id.ivLogo)
        ImageView ivLogo;
        @BindView(R.id.tvDistance)
        TextView tvDistance;
        @BindView(R.id.tvTitle)
        TextView tvName;
        @BindView(R.id.pbLoading)
        ProgressBar pbLoading;

        public CompanyViewHolder(View itemView) {
            super(itemView);

            RxView.clicks(itemView)
                    .subscribe(o -> {
                        Intent intent = new Intent(context, CompanyDescriptionActivity.class);
                        intent.putExtra(TITLE, items.get(getAdapterPosition()).getTitle());
                        intent.putExtra(SALE_ID, items.get(getAdapterPosition()).getSaleId());
                        context.startActivity(intent);
                    });
        }
    }

    class LoadingViewHolder extends BaseViewHolder {
        public LoadingViewHolder(View itemView) {
            super(itemView);
        }
    }
}
