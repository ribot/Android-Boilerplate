package nyc.friendlyrobot.anchor.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import nyc.friendlyrobot.anchor.injection.ActivityComponentFactory;
import nyc.friendlyrobot.anchor.injection.component.ActivityComponent;
import nyc.friendlyrobot.anchor.util.BundleService;

public class BaseActivity extends AppCompatActivity {

    protected ActivityComponent activityComponent;
    private BundleService bundleService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = ActivityComponentFactory.create(this);
        }
        return activityComponent;
    }

    public BundleService getBundleService() {
        return bundleService;
    }

}
