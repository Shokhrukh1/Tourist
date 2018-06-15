package co.example.ui.homePage.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import co.example.R;
import co.example.core.BaseViewHolder;
import co.example.core.ClickableBaseAdapter;

/**
 * Created by Portable-Acer on 06.12.2017.
 */

public class SectionAdapter extends ClickableBaseAdapter<String, SectionAdapter.SectionViewHolder> {
    private List<String> sections;
    private int[] icons;

    public SectionAdapter(List<String> items, int[] icons) {
        super(items);
        this.sections = items;
        this.icons = icons;
    }

    @Override
    public SectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_section, parent, false);

        return new SectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SectionViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.tvName.setText(sections.get(position));
        holder.ivIcon.setImageResource(icons[position]);
    }

    @Override
    protected void onItemClicked(SectionViewHolder holder, int position) {

    }

    class SectionViewHolder extends BaseViewHolder {
        @BindView(R.id.tvTitle)
        TextView tvName;
        @BindView(R.id.ivIcon)
        ImageView ivIcon;

        public SectionViewHolder(View itemView) {
            super(itemView);
        }
    }
}
