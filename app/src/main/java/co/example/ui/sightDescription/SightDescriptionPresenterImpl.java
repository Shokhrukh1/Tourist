package co.example.ui.sightDescription;

import android.text.Html;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import co.example.core.BasePresenterImpl;
import co.example.model.Description;
import co.example.network.ApiManager;
import co.example.utils.PreferencesHelper;

/**
 * Created by Crish on 27.12.2017.
 */

public class SightDescriptionPresenterImpl extends BasePresenterImpl<SightDescriptionView> implements SightDescriptionPresenter {
    private ApiManager apiManager;
    private Disposable disposable;
    private PreferencesHelper preferencesHelper;

    @Inject
    public SightDescriptionPresenterImpl(SightDescriptionView view, ApiManager apiManager, PreferencesHelper preferencesHelper) {
        super(view);
        this.apiManager = apiManager;
        this.preferencesHelper = preferencesHelper;
    }

    @Override
    public void getSightDescription(long id) {
        view.showProgressBar();

        disposable = apiManager.getSightDescription(preferencesHelper.getLanguage(), id).subscribe(s -> {
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("slides");
                List<String> images = new ArrayList<>();
                Description description = new Description();
                description.setTitle(jsonObject.getString("title"));
                description.setText(Html.fromHtml(jsonObject.getString("text")).toString());

                for (int i = 0; i < jsonArray.length(); i++) {
                    images.add(jsonArray.getString(i));
                }

                description.setImages(images);
                view.showSightDescription(description);
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
