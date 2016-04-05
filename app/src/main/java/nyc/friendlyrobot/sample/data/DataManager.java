package nyc.friendlyrobot.sample.data;

import javax.inject.Inject;
import javax.inject.Singleton;

import nyc.friendlyrobot.sample.data.local.AppPreferences;
import nyc.friendlyrobot.sample.data.local.Database;
import nyc.friendlyrobot.sample.data.remote.Api;
import nyc.friendlyrobot.sample.util.EventPosterHelper;

@Singleton
public class DataManager {

    private final Api api;
    private final AppPreferences preferencesHelper;
    private final Database db;
    private final EventPosterHelper mEventPoster;

    @Inject
    public DataManager(Api api, AppPreferences preferencesHelper,
                       Database databaseHelper, EventPosterHelper eventPosterHelper) {
        this.api = api;
        this.preferencesHelper = preferencesHelper;
        db = databaseHelper;
        mEventPoster = eventPosterHelper;
    }


}
