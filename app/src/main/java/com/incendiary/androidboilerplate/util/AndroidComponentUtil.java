package com.incendiary.androidboilerplate.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;

public final class AndroidComponentUtil {

  public static void toggleComponent(Context context, Class componentClass, boolean enable) {
    ComponentName componentName = new ComponentName(context, componentClass);
    PackageManager pm = context.getPackageManager();
    pm.setComponentEnabledSetting(componentName, enable
        ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED
        : PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
  }

  public static boolean isServiceRunning(Context context, Class serviceClass) {
    ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
      if (serviceClass.getName().equals(service.service.getClassName())) {
        return true;
      }
    }
    return false;
  }
}
