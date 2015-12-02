package uk.co.ribot.androidboilerplate.test.common;

import android.app.Application;

import org.mockito.Mockito;

import uk.co.ribot.androidboilerplate.BoilerplateApplication;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.data.local.DatabaseHelper;
import uk.co.ribot.androidboilerplate.data.remote.RibotsService;
import uk.co.ribot.androidboilerplate.test.common.injection.component.DaggerDataManagerTestComponent;
import uk.co.ribot.androidboilerplate.test.common.injection.component.TestComponent;
import uk.co.ribot.androidboilerplate.test.common.injection.module.DataManagerTestModule;

/**
 * Extension of DataManager to be used on a testing environment.
 * It uses DataManagerTestComponent to inject dependencies that are different to the
 * normal runtime ones. e.g. mock objects etc.
 * Whether a dependency is mocked or not depends on the Application class that is passed in its
 * constructor. If the Application is a Mockito mock, then all the helper classes that depend on
 * the Android framework will also be mocked. This scenario usually happens during local unit
 * tests.
 *
 * It also exposes some helpers like the DatabaseHelper or the Retrofit service that are useful
 * during testing.
 */
public class TestDataManager extends DataManager {

    public TestDataManager(Application application) {
        super(application);
    }

    @Override
    protected void injectDependencies(Application application) {
        TestComponent testComponent = (TestComponent)
                ((BoilerplateApplication) application).getComponent();
        // If the Application passed into the DataManager is a mock application (usually during
        // local unit tests), we create a DataManagerTestsModule that injects mock helper classes
        // for helpers that depend on the Android framework.
        DataManagerTestModule module = Mockito.mockingDetails(application).isMock()
                ? new DataManagerTestModule()
                : new DataManagerTestModule(application);
        DaggerDataManagerTestComponent.builder()
                .dataManagerTestModule(module)
                .testComponent(testComponent)
                .build()
                .inject(this);
    }

    public RibotsService getRibotsService() {
        return mRibotsService;
    }

    public DatabaseHelper getDatabaseHelper() {
        return mDatabaseHelper;
    }
}
