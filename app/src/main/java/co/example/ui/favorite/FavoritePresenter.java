package co.example.ui.favorite;

import co.example.core.Presenter;

/**
 * Created by crish on 1/11/18.
 */

public interface FavoritePresenter extends Presenter {
    void getFavorites();
    void removeFavorite(long saleId);
}
