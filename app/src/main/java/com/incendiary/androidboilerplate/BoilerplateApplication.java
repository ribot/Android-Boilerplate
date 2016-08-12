package com.incendiary.androidboilerplate;

import android.app.Application;
import com.crashlytics.android.Crashlytics;
import com.incendiary.androidcommon.AndroidCommon;
import com.incendiary.androidcommon.android.ContextProvider;
import io.fabric.sdk.android.Fabric;
import timber.log.Timber;
import com.incendiary.androidboilerplate.data.local.Preferences;
import com.incendiary.androidboilerplate.di.component.ApplicationComponent;
import com.incendiary.androidboilerplate.di.component.DaggerApplicationComponent;
import com.incendiary.androidboilerplate.di.module.ApplicationModule;

public class BoilerplateApplication extends Application {

  ApplicationComponent mApplicationComponent;

  @Override public void onCreate() {
    super.onCreate();

    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
      Fabric.with(this, new Crashlytics());
    }

    setupStorage();
    setupAndroidCommon();
  }

  private void setupStorage() {
    Preferences.setup(this);
  }

  private void setupAndroidCommon() {
    boolean isDebug = BuildConfig.DEBUG;
    AndroidCommon.with(this).enableStricMode(isDebug).install();
  }

  /* --------------------------------------------------- */
  /* > AppComponent */
  /* --------------------------------------------------- */

  public static BoilerplateApplication get(){
    return (BoilerplateApplication) ContextProvider.get();
  }

  public static ApplicationComponent component() {
    return get().getComponent();
  }

  private ApplicationComponent getComponent() {
    if (mApplicationComponent == null) {
      mApplicationComponent = DaggerApplicationComponent.builder()
          .applicationModule(new ApplicationModule(this))
          .build();
    }
    return mApplicationComponent;
  }

  // Needed to replace the component with a test specific one
  public void setComponent(ApplicationComponent applicationComponent) {
    mApplicationComponent = applicationComponent;
  }
}
