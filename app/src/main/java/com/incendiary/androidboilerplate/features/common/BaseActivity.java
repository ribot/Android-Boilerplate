package com.incendiary.androidboilerplate.features.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import timber.log.Timber;
import com.incendiary.androidboilerplate.BoilerplateApplication;
import com.incendiary.androidboilerplate.di.component.ActivityComponent;
import com.incendiary.androidboilerplate.di.component.ConfigPersistentComponent;
import com.incendiary.androidboilerplate.di.component.DaggerConfigPersistentComponent;
import com.incendiary.androidboilerplate.di.module.ActivityModule;

/**
 * Abstract activity that every other Activity in this application must implement. It handles
 * creation of Dagger components and makes sure that instances of ConfigPersistentComponent survive
 * across configuration changes.
 */
public class BaseActivity extends AppCompatActivity {

  private static final String KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID";
  private static final AtomicLong NEXT_ID = new AtomicLong(0);
  private static final Map<Long, ConfigPersistentComponent> sComponentsMap = new HashMap<>();

  private ActivityComponent mActivityComponent;
  private long mActivityId;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Create the ActivityComponent and reuses cached ConfigPersistentComponent if this is
    // being called after a configuration change.
    mActivityId = savedInstanceState != null
        ? savedInstanceState.getLong(KEY_ACTIVITY_ID)
        : NEXT_ID.getAndIncrement();

    mActivityComponent = getConfigPersistenComponent().activityComponent(new ActivityModule(this));
  }

  private ConfigPersistentComponent getConfigPersistenComponent() {
    ConfigPersistentComponent configPersistentComponent;
    if (!sComponentsMap.containsKey(mActivityId)) {
      Timber.i("Creating new ConfigPersistentComponent id=%d", mActivityId);
      configPersistentComponent = DaggerConfigPersistentComponent.builder()
          .applicationComponent(BoilerplateApplication.component())
          .build();
      sComponentsMap.put(mActivityId, configPersistentComponent);
    } else {
      Timber.i("Reusing ConfigPersistentComponent id=%d", mActivityId);
      configPersistentComponent = sComponentsMap.get(mActivityId);
    }
    return configPersistentComponent;
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putLong(KEY_ACTIVITY_ID, mActivityId);
  }

  @Override protected void onDestroy() {
    if (!isChangingConfigurations()) {
      Timber.i("Clearing ConfigPersistentComponent id=%d", mActivityId);
      sComponentsMap.remove(mActivityId);
    }
    super.onDestroy();
  }

  public ActivityComponent activityComponent() {
    return mActivityComponent;
  }
}
