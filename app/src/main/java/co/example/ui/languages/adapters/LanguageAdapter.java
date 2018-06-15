package co.example.ui.languages.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import co.example.R;
import co.example.core.BaseViewHolder;
import co.example.core.ClickableBaseAdapter;
import co.example.model.Language;

/**
 * Created by Crish on 28.12.2017.
 */

public class LanguageAdapter extends ClickableBaseAdapter<Language, LanguageAdapter.LanguageViewHolder> {
    public LanguageAdapter(List<Language> items) {
        super(items);
    }

    @Override
    protected void onItemClicked(LanguageViewHolder holder, int position) {
    }

    @Override
    public LanguageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new LanguageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LanguageViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.tvName.setText(items.get(position).getTitle());
    }

    class LanguageViewHolder extends BaseViewHolder {
        @BindView(R.id.tvTitle)
        TextView tvName;

        public LanguageViewHolder(View itemView) {
            super(itemView);
        }
    }
}
