package uk.co.ribot.androidboilerplate.injection.module;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import uk.co.ribot.androidboilerplate.data.local.DatabaseHelper;
import uk.co.ribot.androidboilerplate.data.local.PreferencesHelper;
import uk.co.ribot.androidboilerplate.data.remote.RibotsService;
import uk.co.ribot.androidboilerplate.injection.scope.PerDataManager;

/**
 * Provide dependencies to the DataManager, mainly Helper classes and Retrofit services.
 */
@Module
public class DataManagerModule {

    private final Application mApplication;

    // DataManager is a singleton class that don't depend on any Context apart from the Application
    // by taking an application instead of a context we prevent Activity leaks
    public DataManagerModule(Application application) {
        mApplication = application;
    }

    @Provides
    @PerDataManager
    DatabaseHelper provideDatabaseHelper() {
        return new DatabaseHelper(mApplication);
    }

    @Provides
    @PerDataManager
    PreferencesHelper providePreferencesHelper() {
        return new PreferencesHelper(mApplication);
    }

    @Provides
    @PerDataManager
    RibotsService provideRibotsService() {
        return RibotsService.Creator.newRibotsService();
    }
}
