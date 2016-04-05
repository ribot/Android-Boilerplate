package nyc.friendlyrobot.sample.injection.component;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Component;
import nyc.friendlyrobot.sample.data.SyncService;
import nyc.friendlyrobot.sample.data.remote.Api;
import nyc.friendlyrobot.sample.injection.ApplicationContext;
import nyc.friendlyrobot.sample.injection.module.ActivityModule;
import nyc.friendlyrobot.sample.injection.module.ApplicationModule;
import nyc.friendlyrobot.sample.injection.module.NetworkModule;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        NetworkModule.class
})
public interface ApplicationComponent {

    void inject(SyncService syncService);

    @ApplicationContext
    Context context();

    Application application();

    Api ribotsService();

    @NonNull
    ActivityComponent plus(@NonNull ActivityModule activityModule);


}
