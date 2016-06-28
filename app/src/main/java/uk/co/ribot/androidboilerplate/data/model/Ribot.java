package uk.co.ribot.androidboilerplate.data.model;

import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class Ribot implements Comparable<Ribot>, Parcelable {

    public abstract Profile profile();

    public static Ribot create(Profile profile) {
        return new AutoValue_Ribot(profile);
    }

    public static TypeAdapter<Ribot> typeAdapter(Gson gson) {
        return new AutoValue_Ribot.GsonTypeAdapter(gson);
    }

    @Override
    public int compareTo(@NonNull Ribot another) {
        return profile().name().first().compareToIgnoreCase(another.profile().name().first());
    }
}

