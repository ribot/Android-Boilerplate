package nyc.friendlyrobot.anchor.injection;

import android.app.Activity;

import nyc.friendlyrobot.anchor.BoilerplateApplication;
import nyc.friendlyrobot.anchor.injection.component.ActivityComponent;
import nyc.friendlyrobot.anchor.injection.module.ActivityModule;

public final class ActivityComponentFactory {
    private ActivityComponentFactory() {
        //no op
    }

    public static ActivityComponent create(Activity activity) {
        return BoilerplateApplication.get(activity).getComponent()
                .plus(new ActivityModule(activity));
    }
}
