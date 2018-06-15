package co.example.ui.historicalPlaces;

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
import co.example.model.Region;
import co.example.network.ApiManager;
import co.example.utils.PreferencesHelper;

/**
 * Created by Crish on 11.12.2017.
 */

public class HistoricalPlacesPresenterImpl extends BasePresenterImpl<HistoricalPlacesView> implements HistoricalPlacesPresenter {
    private ApiManager apiManager;
    private Disposable disposable;
    private PreferencesHelper preferencesHelper;

    @Inject
    public HistoricalPlacesPresenterImpl(HistoricalPlacesView view, ApiManager apiManager, PreferencesHelper preferencesHelper) {
        super(view);
        this.apiManager = apiManager;
        this.preferencesHelper = preferencesHelper;
    }

    @Override
    public void getHistoricalPlaces() {
        view.showProgressBar();

        disposable = apiManager.getHistoricalPlaces(preferencesHelper.getLanguage())
                .subscribe(s -> {
                    List<Region> regions1 = new ArrayList<>();

                    try {
                        JSONArray jsonArray = new JSONArray(s);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            if (!jsonObject.getString("image").isEmpty() && !jsonObject.getString("name").isEmpty()) {
                                Region region = new Region();
                                region.setRegionId(jsonObject.getLong("region_id"));
                                region.setImageUrl(jsonObject.getString("image"));
                                region.setTitle(jsonObject.getString("title"));
                                region.setName(jsonObject.getString("name"));

                                regions1.add(region);
                            }
                        }

                        view.showHistoricalPlaces(regions1);
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
