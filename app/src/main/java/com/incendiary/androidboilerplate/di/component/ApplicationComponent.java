package com.incendiary.androidboilerplate.di.component;

import android.app.Application;
import android.content.Context;
import dagger.Component;
import javax.inject.Singleton;
import com.incendiary.androidboilerplate.data.DataManager;
import com.incendiary.androidboilerplate.data.SyncService;
import com.incendiary.androidboilerplate.data.local.DatabaseHelper;
import com.incendiary.androidboilerplate.data.remote.ApiService;
import com.incendiary.androidboilerplate.di.ApplicationContext;
import com.incendiary.androidboilerplate.di.module.ApplicationModule;
import com.incendiary.androidboilerplate.di.module.NetworkModule;
import com.incendiary.androidboilerplate.util.RxEventBus;

@Singleton @Component(modules = {
    ApplicationModule.class, NetworkModule.class
}) public interface ApplicationComponent {

  void inject(SyncService syncService);

  @ApplicationContext Context context();

  Application application();
  ApiService apiService();
  DatabaseHelper databaseHelper();
  DataManager dataManager();
  RxEventBus eventBus();
}
