package co.example.ui.map;

import android.util.Log;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import co.example.BuildConfig;
import co.example.core.BasePresenterImpl;
import co.example.network.ApiManager;

/**
 * Created by Crish on 18.12.2017.
 */

public class MapPresenterImpl extends BasePresenterImpl<MapView> implements MapPresenter {
    private ApiManager apiManager;
    private Disposable disposable;

    @Inject
    public MapPresenterImpl(MapView view, ApiManager apiManager) {
        super(view);
        this.apiManager = apiManager;
    }

    @Override
    public void getDirections(double currentLatitude, double currentLongitude, double destinationLatitude, double destinationLongitude) {
        disposable = apiManager.getDirections(String.valueOf(currentLatitude) + String.valueOf(currentLongitude),
                String.valueOf(destinationLatitude) + String.valueOf(destinationLongitude),
                BuildConfig.DIRECTIONS_API_KEY)
                .subscribe(s -> {
                    Log.d("myLogs", s);
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
