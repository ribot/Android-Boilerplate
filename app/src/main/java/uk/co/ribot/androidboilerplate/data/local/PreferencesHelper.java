package uk.co.ribot.androidboilerplate.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import uk.co.ribot.androidboilerplate.injection.ApplicationContext;

@Singleton
public class PreferencesHelper {

    public static final String PREF_FILE_NAME = "android_boilerplate_pref_file";

    private final SharedPreferences mPref;

    @Inject
    public PreferencesHelper(@ApplicationContext Context context) {
        mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void clear() {
        mPref.edit().clear().apply();
    }

}
