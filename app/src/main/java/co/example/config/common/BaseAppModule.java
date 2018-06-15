package co.example.config.common;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import co.example.BuildConfig;
import co.example.App;
import co.example.network.Api;
import co.example.network.ApiManager;
import co.example.utils.PreferencesHelper;

/**
 * Created by Crish on 09.12.2017.
 */

@Module
public abstract class BaseAppModule {
    abstract Application BaseAppModule(App app);

    @Provides
    @Singleton
    static Api provideApi() {
        return  new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build().create(Api.class);
    }

    @Provides
    @Singleton
    static ApiManager provideApiManager(Api api) {
        return new ApiManager(api);
    }

    @Provides
    @Singleton
    static PreferencesHelper getSharedPreferences(Application application) {
        return new PreferencesHelper(application);
    }
}
