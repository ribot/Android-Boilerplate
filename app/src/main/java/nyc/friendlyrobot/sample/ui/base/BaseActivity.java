package nyc.friendlyrobot.sample.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import nyc.friendlyrobot.sample.injection.ActivityComponentFactory;
import nyc.friendlyrobot.sample.injection.component.ActivityComponent;
import nyc.friendlyrobot.sample.util.BundleService;

public class BaseActivity extends AppCompatActivity {

    private ActivityComponent activityComponent;
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
