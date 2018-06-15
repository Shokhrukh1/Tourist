package co.example.ui.favorite;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import lombok.Getter;
import co.example.core.BasePresenterImpl;
import co.example.model.Favorite;
import co.example.network.ApiManager;
import co.example.utils.PreferencesHelper;

/**
 * Created by crish on 1/11/18.
 */

public class FavoritePresenterImpl extends BasePresenterImpl<FavoriteView> implements FavoritePresenter {
    private ApiManager apiManager;
    private PreferencesHelper preferencesHelper;
    private Disposable disposable;
    private List<Favorite> favorites;

    @Inject
    public FavoritePresenterImpl(FavoriteView view, ApiManager apiManager, PreferencesHelper preferencesHelper) {
        super(view);
        this.apiManager = apiManager;
        this.preferencesHelper = preferencesHelper;
        this.favorites = new ArrayList<>();
    }

    @Override
    public void getFavorites() {
        view.showProgressBar();

        disposable = apiManager.getFavorites(preferencesHelper.getLanguage(), preferencesHelper.getUserId())
                .subscribe(s -> {
                    JSONArray jsonResponse = new JSONArray(s);

                    if (jsonResponse.length() == 0) {
                        view.showNoFavorites();
                        return;
                    }

                    for (int i = 0; i < jsonResponse.length(); i++) {
                        JSONObject jsonFavorite = jsonResponse.getJSONObject(i);
                        Favorite favorite = new Favorite();

                        favorite.setSaleId(jsonFavorite.getLong("sale_id"));
                        favorite.setTitle(jsonFavorite.getString("title"));
                        favorite.setText(jsonFavorite.getString("text"));
                        favorite.setImage(jsonFavorite.getString("image"));

                        favorites.add(favorite);
                    }

                    view.showFavorites(favorites);
                    view.showData();
                }, throwable -> {
                });
    }

    @Override
    public void removeFavorite(long saleId) {
        for (int i = 0; i < favorites.size(); i++) {
            if (favorites.get(i).getSaleId() == saleId) {
                favorites.remove(i);
                view.favoriteRemoved(i);
                break;
            }
        }

        if (favorites.isEmpty()) {
            view.showNoFavorites();
        }
    }

    @Override
    public void onDestroy() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }

        super.onDestroy();
    }
}
