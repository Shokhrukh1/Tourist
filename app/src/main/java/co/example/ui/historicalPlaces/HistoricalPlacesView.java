package co.example.ui.historicalPlaces;

import java.util.List;

import co.example.core.BaseView;
import co.example.model.Region;

/**
 * Created by Crish on 11.12.2017.
 */

public interface HistoricalPlacesView extends BaseView {
    void showHistoricalPlaces(List<Region> regions);
}
