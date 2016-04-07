package nyc.friendlyrobot.anchor.injection.component;

import dagger.Subcomponent;
import nyc.friendlyrobot.anchor.injection.ScopeActivity;
import nyc.friendlyrobot.anchor.injection.module.ActivityModule;
import nyc.friendlyrobot.anchor.injection.module.BundleModule;
import nyc.friendlyrobot.anchor.ui.main.MainActivity;

/**
 * This component inject dependencies to all Activities across the application
 */
@Subcomponent(modules = {
        ActivityModule.class, BundleModule.class})
@ScopeActivity
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
