package nyc.friendlyrobot.anchor.ui.utils;

import android.graphics.Typeface;

import java.util.HashMap;
import java.util.Map;


/**
 * we need to cache fonts that we use, good form and
 * performance
 */
public final class TypefaceCache {

    private static Map<String, Typeface> sFontCache = new HashMap<>(10);

    private TypefaceCache() {
        // empty
    }

    public static Typeface get(String name) {
        return sFontCache.get(name);
    }

    public static void put(String name, Typeface typeface) {
        sFontCache.put(name, typeface);
    }
}
