package uk.co.ribot.androidboilerplate.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import uk.co.ribot.androidboilerplate.BoilerplateApplication;
import uk.co.ribot.androidboilerplate.injection.component.ActivityComponent;
import uk.co.ribot.androidboilerplate.injection.component.DaggerActivityComponent;
import uk.co.ribot.androidboilerplate.injection.module.ActivityModule;

public class BaseActivity extends AppCompatActivity {

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(BoilerplateApplication.get(this).getComponent())
                    .build();
        }
        return mActivityComponent;
    }

}
