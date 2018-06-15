package co.example.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import java.util.List;

import co.example.BuildConfig;
import co.example.R;
import co.example.listeners.GlideRequestListener;

/**
 * Created by crish on 1/4/18.
 */

public class ImagePagerAdapter extends PagerAdapter {
    private Context context;
    private List<String> images;

    public ImagePagerAdapter(Context context, List<String> images) {
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, container, false);

        ImageView ivImage = view.findViewById(R.id.ivImage);
        ProgressBar pbLoading = view.findViewById(R.id.pbLoading);

        Glide.with(context)
                .load(BuildConfig.BASE_URL + images.get(position))
                .listener(new GlideRequestListener(ivImage, pbLoading))
                .into(ivImage);

        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
