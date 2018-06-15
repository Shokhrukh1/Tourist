package co.example.ui.currencyExchange.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import co.example.R;
import co.example.core.BaseAdapter;
import co.example.core.BaseViewHolder;
import co.example.model.Currency;

/**
 * Created by Crish on 14.12.2017.
 */

public class CurrencyAdapter extends BaseAdapter<Currency, CurrencyAdapter.CurrencyViewHolder> {
    private Context context;

    public CurrencyAdapter(Context context, List<Currency> items) {
        super(items);
        this.context = context;
    }

    @Override
    public CurrencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new CurrencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CurrencyViewHolder holder, int position) {
        holder.tvName.setText(items.get(position).getTitle() + " = " + items.get(position).getValue() + " " + context.getString(R.string.sum));
    }

    class CurrencyViewHolder extends BaseViewHolder {
        @BindView(R.id.tvTitle)
        TextView tvName;

        public CurrencyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
