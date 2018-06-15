package co.example.ui.favorite;

import java.util.List;

import co.example.core.BaseView;
import co.example.model.Favorite;

/**
 * Created by crish on 1/11/18.
 */

public interface FavoriteView extends BaseView {
    void showFavorites(List<Favorite>favorites);
    void favoriteRemoved(int position);
    void showNoFavorites();
}
