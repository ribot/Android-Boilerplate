package nyc.friendlyrobot.anchor.data;

import javax.inject.Inject;
import javax.inject.Singleton;

import nyc.friendlyrobot.anchor.data.local.AppPreferences;
import nyc.friendlyrobot.anchor.data.local.Database;
import nyc.friendlyrobot.anchor.data.remote.Api;
import nyc.friendlyrobot.anchor.util.EventPosterHelper;

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
