package nyc.friendlyrobot.sample.androidboilerplate.test.common.rules;

import android.content.Context;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import nyc.friendlyrobot.sample.BoilerplateApplication;
import nyc.friendlyrobot.sample.androidboilerplate.test.common.injection.component.DaggerTestComponent;
import nyc.friendlyrobot.sample.androidboilerplate.test.common.injection.component.TestComponent;
import nyc.friendlyrobot.sample.androidboilerplate.test.common.injection.module.ApplicationTestModule;

/**
 * Test rule that creates and sets a Dagger TestComponent into the application overriding the
 * existing application component.
 * Use this rule in your test case in order for the app to use mock dependencies.
 * It also exposes some of the dependencies so they can be easily accessed from the tests, e.g. to
 * stub mocks etc.
 */
public class TestComponentRule implements TestRule {

    private final TestComponent mTestComponent;
    private final Context mContext;

    public TestComponentRule(Context context) {
        mContext = context;
        BoilerplateApplication application = BoilerplateApplication.get(context);
        mTestComponent = DaggerTestComponent.builder()
                .applicationTestModule(new ApplicationTestModule(application))
                .build();
    }

    public Context getContext() {
        return mContext;
    }

//    public DataManager getMockDataManager() {
//        return mTestComponent.dataManager();
//    }

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                BoilerplateApplication application = BoilerplateApplication.get(mContext);
                application.setComponent(mTestComponent);
                base.evaluate();
                application.setComponent(null);
            }
        };
    }
}
