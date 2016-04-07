package nyc.friendlyrobot.anchor.data.local;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by brianplummer on 2/20/16.
 */
@Singleton
public class AppPreferences {
    public static final String AUTH_TOKEN = "token";
    public static final String LOGIN_REQUEST = "login";
    private final SharedPreferences sharedPreferences;

    @Inject
    public AppPreferences(Application context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @SuppressLint("CommitPrefEdits")
    public void applyPreference(String key, boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getPreference(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    @SuppressLint("CommitPrefEdits")
    public void applyPreference(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getPreference(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public void registerPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener);
    }

    public void unregisterPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener);
    }

}
