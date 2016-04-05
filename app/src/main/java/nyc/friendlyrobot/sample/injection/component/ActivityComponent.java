package nyc.friendlyrobot.sample.injection.component;

import dagger.Subcomponent;
import nyc.friendlyrobot.sample.injection.ScopeActivity;
import nyc.friendlyrobot.sample.injection.module.ActivityModule;
import nyc.friendlyrobot.sample.injection.module.BundleModule;
import nyc.friendlyrobot.sample.ui.main.MainActivity;

/**
 * This component inject dependencies to all Activities across the application
 */
@Subcomponent(modules = {
        ActivityModule.class, BundleModule.class})
@ScopeActivity
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
