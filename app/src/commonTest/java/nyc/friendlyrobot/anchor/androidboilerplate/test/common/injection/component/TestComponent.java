package nyc.friendlyrobot.anchor.androidboilerplate.test.common.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import nyc.friendlyrobot.anchor.androidboilerplate.test.common.injection.module.ApplicationTestModule;
import nyc.friendlyrobot.anchor.injection.component.ApplicationComponent;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {

}
