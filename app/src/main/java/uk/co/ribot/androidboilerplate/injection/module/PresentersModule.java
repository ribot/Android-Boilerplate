package uk.co.ribot.androidboilerplate.injection.module;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import uk.co.ribot.androidboilerplate.injection.scope.PerActivity;
import uk.co.ribot.androidboilerplate.ui.main.MainPresenter;

/**
 * This module provides instances of Presenters.
 */
@Module
public class PresentersModule {

    private Application mApplication;

    public PresentersModule(Application application) {
        mApplication = application;
    }

    @Provides
    @PerActivity
    MainPresenter providesMainPresenter() {
        return new MainPresenter(mApplication);
    }

}
