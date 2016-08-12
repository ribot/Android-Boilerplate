package com.incendiary.androidboilerplate.di.component;

import dagger.Subcomponent;
import com.incendiary.androidboilerplate.di.PerActivity;
import com.incendiary.androidboilerplate.di.module.ActivityModule;
import com.incendiary.androidboilerplate.features.main.MainActivity;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity @Subcomponent(modules = ActivityModule.class) public interface ActivityComponent {
  void inject(MainActivity mainActivity);
}
