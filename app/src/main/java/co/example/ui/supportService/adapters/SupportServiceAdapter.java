package co.example.ui.supportService.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import co.example.R;
import co.example.core.BaseAdapter;
import co.example.core.BaseViewHolder;

/**
 * Created by Crish on 14.12.2017.
 */

public class SupportServiceAdapter extends BaseAdapter<String, SupportServiceAdapter.SupportServiceViewHolder> {
    private int[] flags;

    public SupportServiceAdapter(List<String> items, int[] flags) {
        super(items);
        this.flags = flags;
    }

    @Override
    public SupportServiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flag, parent, false);

        return new SupportServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SupportServiceViewHolder holder, int position) {
        holder.tvLanguage.setText(items.get(position));
        holder.ivFlag.setImageResource(flags[position]);
    }

    class SupportServiceViewHolder extends BaseViewHolder {
        @BindView(R.id.tvLanguage)
        TextView tvLanguage;
        @BindView(R.id.ivFlag)
        ImageView ivFlag;

        public SupportServiceViewHolder(View itemView) {
            super(itemView);
        }
    }
}
