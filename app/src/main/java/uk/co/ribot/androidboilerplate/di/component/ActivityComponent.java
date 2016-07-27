package uk.co.ribot.androidboilerplate.di.component;

import dagger.Subcomponent;
import uk.co.ribot.androidboilerplate.di.PerActivity;
import uk.co.ribot.androidboilerplate.di.module.ActivityModule;
import uk.co.ribot.androidboilerplate.ui.main.MainActivity;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity @Subcomponent(modules = ActivityModule.class) public interface ActivityComponent {
  void inject(MainActivity mainActivity);
}
