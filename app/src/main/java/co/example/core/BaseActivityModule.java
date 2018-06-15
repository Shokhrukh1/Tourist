package co.example.core;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.location.LocationRequest;
import com.tbruyelle.rxpermissions2.RxPermissions;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider;
import co.example.config.scope.PerActivity;

/**
 * Created by Portable-Acer on 05.12.2017.
 */

@Module
public class BaseActivityModule {
    @Provides
    @PerActivity
    static RxPermissions provideRxPermissions(AppCompatActivity activity) {
        return new RxPermissions(activity);
    }

    @Provides
    @PerActivity
    static Observable<Location> provideReactiveLocation(AppCompatActivity activity) {
        return new ReactiveLocationProvider(activity)
                .getUpdatedLocation(LocationRequest.create()
                        .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                        .setNumUpdates(1));
    }
}
