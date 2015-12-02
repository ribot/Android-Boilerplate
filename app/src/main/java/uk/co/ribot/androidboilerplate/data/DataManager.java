package uk.co.ribot.androidboilerplate.data;

import android.app.Application;

import com.squareup.otto.Bus;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;
import uk.co.ribot.androidboilerplate.BoilerplateApplication;
import uk.co.ribot.androidboilerplate.data.local.DatabaseHelper;
import uk.co.ribot.androidboilerplate.data.local.PreferencesHelper;
import uk.co.ribot.androidboilerplate.data.model.Ribot;
import uk.co.ribot.androidboilerplate.data.remote.RibotsService;
import uk.co.ribot.androidboilerplate.injection.component.DaggerDataManagerComponent;
import uk.co.ribot.androidboilerplate.injection.module.DataManagerModule;

public class DataManager {

    @Inject protected RibotsService mRibotsService;
    @Inject protected DatabaseHelper mDatabaseHelper;
    @Inject protected PreferencesHelper mPreferencesHelper;
    @Inject protected Bus mBus;

    public DataManager(Application application) {
        injectDependencies(application);
    }

    protected void injectDependencies(Application application) {
        DaggerDataManagerComponent.builder()
                .applicationComponent(((BoilerplateApplication) application).getComponent())
                .dataManagerModule(new DataManagerModule(application))
                .build()
                .inject(this);
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    public Observable<Ribot> syncRibots() {
        return mRibotsService.getRibots()
                .concatMap(new Func1<List<Ribot>, Observable<Ribot>>() {
                    @Override
                    public Observable<Ribot> call(List<Ribot> ribots) {
                        return mDatabaseHelper.setRibots(ribots);
                    }
                });
    }

    public Observable<List<Ribot>> getRibots() {
        return mDatabaseHelper.getRibots().distinct();
    }

}
