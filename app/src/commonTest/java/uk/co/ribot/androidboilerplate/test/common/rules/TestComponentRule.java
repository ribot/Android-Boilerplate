package uk.co.ribot.androidboilerplate.test.common.rules;

import android.content.Context;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import uk.co.ribot.androidboilerplate.BoilerplateApplication;
import uk.co.ribot.androidboilerplate.data.local.DatabaseHelper;
import uk.co.ribot.androidboilerplate.data.local.PreferencesHelper;
import uk.co.ribot.androidboilerplate.data.remote.RibotsService;
import uk.co.ribot.androidboilerplate.test.common.TestDataManager;
import uk.co.ribot.androidboilerplate.test.common.injection.component.DaggerTestComponent;
import uk.co.ribot.androidboilerplate.test.common.injection.component.TestComponent;
import uk.co.ribot.androidboilerplate.test.common.injection.module.ApplicationTestModule;

/**
 * Test rule that creates and sets a Dagger TestComponent into the application overriding the
 * existing application component.
 * Use this rule in your test case in order for the app to use mock dependencies.
 * It also exposes some of the dependencies so they can be easily accessed from the tests, e.g. to
 * stub mocks etc.
 */
public class TestComponentRule implements TestRule {

    private TestComponent mTestComponent;
    private Context mContext;
    private ApplicationTestModule.DataManagerTestStrategy mDataManagerTestStrategy;

    /**
     * Create a rule that sets up a Dagger TestComponent to inject test dependencies such as mocks.
     *
     * It takes a dataManagerTestStrategy that can be:
     * 1) REAL: injects real instances of the DataManager
     * 2) MOCK: injects Mockito mock instances of the DataManager that must be stubbed
     * 3) SPY: injects an Mockito spy instance of the DataManager that can be partially stubbed.
     * You can read more about Mockito spies and mocks to help you choose the best
     * strategy for your tests.
     */
    public TestComponentRule(Context context,
                             ApplicationTestModule.DataManagerTestStrategy dmTestStrategy) {
        init(context, dmTestStrategy);
    }

    /**
     * Create a rule that sets up a Dagger TestComponent to inject test dependencies such as mocks.
     * It will use the default dataManagerTestStrategy that is REAL.
     */
    public TestComponentRule(Context context) {
        init(context, ApplicationTestModule.DataManagerTestStrategy.REAL);
    }

    private void init(Context context,
                      ApplicationTestModule.DataManagerTestStrategy dataManagerTestStrategy) {
        mContext = context;
        mDataManagerTestStrategy = dataManagerTestStrategy;
    }

    public TestComponent getTestComponent() {
        return mTestComponent;
    }

    public Context getContext() {
        return mContext;
    }

    public ApplicationTestModule.DataManagerTestStrategy getDataManagerTestStrategy() {
        return mDataManagerTestStrategy;
    }

    /**
     * This could return a real instance, a Mockito.mock or a Mockito.spy depending on the
     * strategy chosen. You can use {@link #getDataManagerTestStrategy()} to get the current
     * strategy.
     */
    public TestDataManager getDataManager() {
        return (TestDataManager) mTestComponent.dataManager();
    }

    public RibotsService getMockRibotsService() {
        return getDataManager().getRibotsService();
    }

    public DatabaseHelper getDatabaseHelper() {
        return getDataManager().getDatabaseHelper();
    }

    public PreferencesHelper getPreferencesHelper() {
        return getDataManager().getPreferencesHelper();
    }

    private void setupDaggerTestComponentInApplication() {
        BoilerplateApplication application = BoilerplateApplication.get(mContext);
        ApplicationTestModule module = new ApplicationTestModule(application,
                mDataManagerTestStrategy);
        mTestComponent = DaggerTestComponent.builder()
                .applicationTestModule(module)
                .build();
        application.setComponent(mTestComponent);
    }

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    setupDaggerTestComponentInApplication();
                    base.evaluate();
                } finally {
                    mTestComponent = null;
                }
            }
        };
    }
}
