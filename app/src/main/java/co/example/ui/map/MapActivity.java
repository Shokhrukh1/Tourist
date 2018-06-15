package co.example.ui.map;

import android.Manifest;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import co.example.R;
import co.example.core.BaseActivity;
import co.example.utils.PreferencesHelper;
import co.example.utils.Utils;

import static co.example.ui.companyList.Constants.CATEGORY_ID;
import static co.example.ui.companyList.Constants.DISTRICT_ID;
import static co.example.ui.companyList.Constants.REGION_ID;
import static co.example.ui.companyList.Constants.SEARCH_TEXT;
import static co.example.ui.map.Constants.LATITUDE;
import static co.example.ui.map.Constants.LONGITUDE;

public class MapActivity extends BaseActivity implements MapView, OnMapReadyCallback {
    @Inject
    MapPresenter presenter;
    @Inject
    RxPermissions rxPermissions;
    @Inject
    Observable<Location> location;
    private Disposable locationDisposable;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        toolbar.setTitle(R.string.map);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        showProgressBar();

        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                .getMapAsync(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_map;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (Utils.isNetworkAvailable(this)) {
            rxPermissions.requestEach(Manifest.permission.ACCESS_FINE_LOCATION)
                    .subscribe(permission -> {
                        if (permission.granted) {
                            locationDisposable = location.subscribe(location -> {
                                presenter.getDirections(
                                        location.getLatitude(),
                                        location.getLongitude(),
                                        41.315212299925,
                                        69.315731058426
                                );
                            });
                        } else if (permission.shouldShowRequestPermissionRationale) {
                        } else {
                        }
                    });
        } else {
            showNoNetwork();
        }

        Log.d("myLogs", "onMapReady");
        //https://maps.googleapis.com/maps/api/directions/json?destination=41.315212299925,69.315731058426&origin=41.4676501,69.5827013&key=AIzaSyCMKC3Qt_2smydjL3V6T5s2rl7CeCC9u64
        googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(41.315212299925, 69.315731058426))
                .add(new LatLng(41.4676501, 69.5827013)));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(41.315212299925, 69.315731058426), 10));
        /*LatLng sydney = new LatLng(-34, 151);
        googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }

    @Override
    protected void onStop() {
        if (locationDisposable != null) {
            locationDisposable.dispose();
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void getData() {

    }
}
