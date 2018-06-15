package co.example.ui.search.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import co.example.R;
import co.example.model.search.Category;

/**
 * Created by Crish on 27.12.2017.
 */

public class CategoryAdapter extends ArrayAdapter<Category> {
    private List<Category> categories;

    public CategoryAdapter(@NonNull Context context, int resource, List<Category> categories) {
        super(context, resource);
        this.categories = categories;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_spinner_item, parent, false);
        ((TextView) view.findViewById(android.R.id.text1)).setText(categories.get(position).getName());

        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        ((TextView) view.findViewById(android.R.id.text1)).setText(categories.get(position).getName());

        return view;
    }
}
