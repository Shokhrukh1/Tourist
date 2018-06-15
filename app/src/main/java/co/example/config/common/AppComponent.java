package co.example.config.common;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import co.example.App;

/**
 * Created by Portable-Acer on 05.12.2017.
 */
@Singleton
@Component(modules = {AppModule.class, BaseAppModule.class})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

    void inject(App app);
}
