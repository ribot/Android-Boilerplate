package nyc.friendlyrobot.anchor.util;

import android.os.Bundle;

public class BundleService {
    private final Bundle data;
     public Bundle savedState;

    public BundleService(Bundle savedState, Bundle intentExtras) {
        data = new Bundle();

        this.savedState = savedState;
        if (this.savedState != null) {
            data.putAll(savedState);
        }
        if (intentExtras != null) {
            data.putAll(intentExtras);
        }
    }

    public Object get(String key) {
        return data.get(key);
    }

    public boolean contains(String key) {
        return data.containsKey(key);
    }

    public Bundle getAll() {
        return data;
    }
}
