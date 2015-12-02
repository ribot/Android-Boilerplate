package uk.co.ribot.androidboilerplate.util;


import org.mockito.Mockito;

import uk.co.ribot.androidboilerplate.BoilerplateApplication;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.test.common.injection.component.DaggerTestComponent;
import uk.co.ribot.androidboilerplate.test.common.injection.component.TestComponent;
import uk.co.ribot.androidboilerplate.test.common.injection.module.ApplicationTestModule;

import static org.mockito.Mockito.when;

/**
 * Set up a mock application object that returns a Dagger TestComponent when calling
 * application.getComponent().
 *
 * It exposes the mock dependencies such as the mock application or the mock DataManger so they
 * can be used from the tests.
 *
 * This helper is aim to be used during local unit tests when a real application is not available.
 * If you are using Espresso or Robolectric you may want to use
 * {@link .test.common.rules.TestComponentRule} instead.
 */
public class MockDependenciesHelper {

    BoilerplateApplication mMockApplication;
    TestComponent mTestComponent;

    public MockDependenciesHelper() {
        mMockApplication = Mockito.mock(BoilerplateApplication.class);
        ApplicationTestModule applicationTestModule = new ApplicationTestModule(mMockApplication,
                ApplicationTestModule.DataManagerTestStrategy.MOCK);
        mTestComponent = DaggerTestComponent.builder()
                .applicationTestModule(applicationTestModule)
                .build();

        when(mMockApplication.getComponent())
                .thenReturn(mTestComponent);
    }

    public BoilerplateApplication getMockApplication() {
        return mMockApplication;
    }

    public DataManager getMockDataManager() {
        return mTestComponent.dataManager();
    }

}
