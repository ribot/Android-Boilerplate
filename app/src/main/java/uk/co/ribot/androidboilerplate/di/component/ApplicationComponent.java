package uk.co.ribot.androidboilerplate.di.component;

import android.app.Application;
import android.content.Context;
import dagger.Component;
import javax.inject.Singleton;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.data.SyncService;
import uk.co.ribot.androidboilerplate.data.local.DatabaseHelper;
import uk.co.ribot.androidboilerplate.data.local.PreferencesHelper;
import uk.co.ribot.androidboilerplate.data.remote.ApiService;
import uk.co.ribot.androidboilerplate.di.ApplicationContext;
import uk.co.ribot.androidboilerplate.di.module.ApplicationModule;
import uk.co.ribot.androidboilerplate.di.module.NetworkModule;
import uk.co.ribot.androidboilerplate.util.RxEventBus;

@Singleton @Component(modules = {
    ApplicationModule.class, NetworkModule.class
}) public interface ApplicationComponent {

  void inject(SyncService syncService);

  @ApplicationContext Context context();

  Application application();
  ApiService ribotsService();
  PreferencesHelper preferencesHelper();
  DatabaseHelper databaseHelper();
  DataManager dataManager();
  RxEventBus eventBus();
}
