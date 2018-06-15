package co.example.ui.regionDescription;

import android.text.Html;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import co.example.core.BasePresenterImpl;
import co.example.model.Description;
import co.example.network.ApiManager;
import co.example.utils.PreferencesHelper;

/**
 * Created by Crish on 18.12.2017.
 */

public class RegionDescriptionPresenterImpl extends BasePresenterImpl<RegionDescriptionView> implements RegionDescriptionPresenter {
    private ApiManager apiManager;
    private Disposable disposable;
    private PreferencesHelper preferencesHelper;

    @Inject
    public RegionDescriptionPresenterImpl(RegionDescriptionView view, ApiManager apiManager, PreferencesHelper preferencesHelper) {
        super(view);
        this.apiManager = apiManager;
        this.preferencesHelper = preferencesHelper;
    }

    @Override
    public void getRegionDescription(long id) {
        view.showProgressBar();

        disposable = apiManager.getRegionDescription(preferencesHelper.getLanguage(), id).subscribe(s -> {
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonImages = jsonObject.getJSONObject("slides").getJSONArray("slides");
                List<String> images = new ArrayList<>();
                Description description = new Description();
                description.setTitle(jsonObject.getString("title"));
                description.setText(Html.fromHtml(jsonObject.getString("description")).toString());

                for (int i = 0; i < jsonImages.length(); i++) {
                    images.add(jsonImages.getString(i));
                }

                description.setImages(images);
                view.showRegionDescription(description);
                view.showData();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, throwable -> {

        });
    }

    @Override
    public void onDestroy() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        super.onDestroy();
    }
}
