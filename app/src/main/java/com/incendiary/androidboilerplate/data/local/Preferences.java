package com.incendiary.androidboilerplate.data.local;

import android.app.Application;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.LogLevel;
import com.incendiary.androidboilerplate.BuildConfig;

public class Preferences {

  private static Preferences INSTANCE;

  public static Preferences getInstance() {
    if (INSTANCE == null) INSTANCE = new Preferences();
    return INSTANCE;
  }

  /* --------------------------------------------------- */
  /* > Setup */
  /* --------------------------------------------------- */

  public static void setup(Application application) {
    Hawk.init(application)
        .setEncryptionMethod(HawkBuilder.EncryptionMethod.MEDIUM)
        .setLogLevel(BuildConfig.DEBUG
            ? LogLevel.FULL
            : LogLevel.NONE)
        .buildRx()
        .subscribe();
  }
}
