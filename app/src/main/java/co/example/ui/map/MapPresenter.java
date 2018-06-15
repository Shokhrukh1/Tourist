package co.example.ui.map;

import co.example.core.Presenter;

/**
 * Created by Crish on 18.12.2017.
 */

public interface MapPresenter extends Presenter {
    void getDirections(double currentLatitude, double currentLongitude, double destinationLatitude, double destinationLongitude);
}
