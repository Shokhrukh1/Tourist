package co.example;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import java.util.Locale;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import co.example.config.common.DaggerAppComponent;
import co.example.utils.PreferencesHelper;
import co.example.utils.Utils;

public class App extends Application implements HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;
    @Inject
    PreferencesHelper preferencesHelper;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);

        if (!preferencesHelper.containsLanguage()) {
            preferencesHelper.setLanguage(Utils.convertLangTextFormatToLangId(Locale.getDefault().getLanguage()));
        }
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}