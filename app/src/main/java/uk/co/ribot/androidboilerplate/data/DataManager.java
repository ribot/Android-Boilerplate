package uk.co.ribot.androidboilerplate.data;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Func1;
import uk.co.ribot.androidboilerplate.data.local.DatabaseHelper;
import uk.co.ribot.androidboilerplate.data.local.PreferencesHelper;
import uk.co.ribot.androidboilerplate.data.model.Ribot;
import uk.co.ribot.androidboilerplate.data.remote.RibotsService;
import uk.co.ribot.androidboilerplate.util.EventPosterHelper;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class DataManager {

    private final RibotsService mRibotsService;
    private final DatabaseHelper mDatabaseHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final EventPosterHelper mEventPoster;

    @Inject
    public DataManager(RibotsService ribotsService, PreferencesHelper preferencesHelper,
                       DatabaseHelper databaseHelper, EventPosterHelper eventPosterHelper) {
        mRibotsService = ribotsService;
        mPreferencesHelper = preferencesHelper;
        mDatabaseHelper = databaseHelper;
        mEventPoster = eventPosterHelper;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    public Observable<List<Ribot>> syncRibots() {
        return mRibotsService.getRibots().map(new Func1<List<Ribot>, List<Ribot>>() {
            @Override
            public List<Ribot> call(List<Ribot> ribots) {
                mDatabaseHelper.setRibots(ribots).subscribe();
                return ribots;
            }
        });
    }

    public Observable<List<Ribot>> getRibots(boolean isNetworkConnected) {
        if (isNetworkConnected) {
            return Observable.merge(mDatabaseHelper.getRibots(), syncRibots()).distinct();
        } else {
            return mDatabaseHelper.getRibots().distinct();
        }
    }

    /// Helper method to post events from doOnCompleted.
    private Action0 postEventAction(final Object event) {
        return new Action0() {
            @Override
            public void call() {
                mEventPoster.postEventSafely(event);
            }
        };
    }

}
