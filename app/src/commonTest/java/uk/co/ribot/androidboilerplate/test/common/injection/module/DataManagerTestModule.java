package uk.co.ribot.androidboilerplate.test.common.injection.module;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import uk.co.ribot.androidboilerplate.data.local.DatabaseHelper;
import uk.co.ribot.androidboilerplate.data.local.PreferencesHelper;
import uk.co.ribot.androidboilerplate.data.remote.RibotsService;
import uk.co.ribot.androidboilerplate.injection.scope.PerDataManager;

import static org.mockito.Mockito.mock;

/**
 * Provides dependencies for an app running on a testing environment
 * This allows injecting mocks if necessary.
 */
@Module
public class DataManagerTestModule {

    private final Application mApplication;

    /**
     * Create a module that provides helpers that don't depend on the Android framework.
     * Any helper that heavily relies on the framework like DatabaseHelper or PreferencesHelper
     * will be mocked.
     * This is very useful for local unit tests.
     */
    public DataManagerTestModule() {
        mApplication = null;
    }

    /**
     * Create a module that provides real helpers but mocks those helpers that access external
     * resources such as REST APIs.
     */
    public DataManagerTestModule(Application application) {
        mApplication = application;
    }

    @Provides
    @PerDataManager
    DatabaseHelper provideDatabaseHelper() {
        return mApplication == null ? mock(DatabaseHelper.class) :
                new DatabaseHelper(mApplication);
    }

    @Provides
    @PerDataManager
    PreferencesHelper providePreferencesHelper() {
        return mApplication == null ? mock(PreferencesHelper.class) :
                new PreferencesHelper(mApplication);
    }

    @Provides
    @PerDataManager
    RibotsService provideRibotsService() {
        return mock(RibotsService.class);
    }
}
