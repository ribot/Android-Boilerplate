package uk.co.ribot.androidboilerplate.di.module;

import android.app.Application;
import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import retrofit2.Retrofit;
import uk.co.ribot.androidboilerplate.data.remote.ApiService;
import uk.co.ribot.androidboilerplate.di.ApplicationContext;

/**
 * Provide application-level dependencies.
 */
@Module public class ApplicationModule {
  protected final Application mApplication;

  public ApplicationModule(Application application) {
    mApplication = application;
  }

  @Provides Application provideApplication() {
    return mApplication;
  }

  @Provides @ApplicationContext Context provideContext() {
    return mApplication;
  }

  @Provides @Singleton ApiService provideRibotsService(Retrofit retrofit) {
    return ApiService.Creator.newRibotsService(retrofit);
  }
}
