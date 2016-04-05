package nyc.friendlyrobot.sample.androidboilerplate.test.common.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import nyc.friendlyrobot.sample.injection.component.ApplicationComponent;
import nyc.friendlyrobot.sample.androidboilerplate.test.common.injection.module.ApplicationTestModule;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {

}
