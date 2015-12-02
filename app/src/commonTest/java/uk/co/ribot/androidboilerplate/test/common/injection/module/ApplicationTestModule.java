package uk.co.ribot.androidboilerplate.test.common.injection.module;

import android.app.Application;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.test.common.TestDataManager;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

/**
 * Provides application-level dependencies for an app running on a testing environment
 * This allows injecting mocks if necessary.
 */
@Module
public class ApplicationTestModule {

    public enum DataManagerTestStrategy { REAL, MOCK, SPY }

    private final Application mApplication;
    private DataManagerTestStrategy mDataManagerTestStrategy;

    /**
     * Create a module that provides application dependencies for a testing environment.
     * It takes a dataManagerTestStrategy that can be 1) REAL: provides real instances of
     * the DataManager, 2) MOCK: provides Mockito mock instances of the DataManager that must be
     * stubbed, 3) SPY: provides an Mockito spy instance of the DataManager that can be partially
     * stubbed, you can read more about Mockito spies and mocks to help you choose the best
     * strategy for your tests.
     */
    public ApplicationTestModule(Application application,
                                 DataManagerTestStrategy dataManagerTestStrategy) {
        mApplication = application;
        mDataManagerTestStrategy = dataManagerTestStrategy;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    DataManager provideDataManager() {
        switch (mDataManagerTestStrategy) {
            case MOCK:
                return mock(TestDataManager.class);
            case SPY:
                return spy(new TestDataManager(mApplication));
            default:
                return new TestDataManager(mApplication);
        }
    }

    @Provides
    @Singleton
    Bus provideEventBus() {
        return new Bus();
    }
}
