package nyc.friendlyrobot.anchor.injection.component;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Component;
import nyc.friendlyrobot.anchor.BoilerplateApplication;
import nyc.friendlyrobot.anchor.injection.module.ActivityModule;
import nyc.friendlyrobot.anchor.injection.module.ApplicationModule;
import nyc.friendlyrobot.anchor.injection.module.NetworkModule;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        NetworkModule.class,
})
public interface ApplicationComponent {
    @NonNull
    ActivityComponent plus(@NonNull ActivityModule activityModule);

    void inject(@NonNull BoilerplateApplication boxBeeApplication);
}
