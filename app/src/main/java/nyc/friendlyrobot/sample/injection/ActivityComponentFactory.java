package nyc.friendlyrobot.sample.injection;

import android.app.Activity;

import nyc.friendlyrobot.sample.BoilerplateApplication;
import nyc.friendlyrobot.sample.injection.component.ActivityComponent;
import nyc.friendlyrobot.sample.injection.module.ActivityModule;

public final class ActivityComponentFactory {
    private ActivityComponentFactory() {
        //no op
    }

    public static ActivityComponent create(Activity activity) {
        return BoilerplateApplication.get(activity).getComponent()
                .plus(new ActivityModule(activity));
    }
}
