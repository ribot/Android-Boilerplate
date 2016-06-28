package uk.co.ribot.androidboilerplate.data.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.Date;

@AutoValue
public abstract class Profile implements Parcelable {
    public abstract Name name();
    public abstract String email();
    public abstract String hexColor();
    public abstract Date dateOfBirth();
    @Nullable public abstract String bio();
    @Nullable public abstract String avatar();

    public static Builder builder() {
        return new AutoValue_Profile.Builder();
    }

    public static TypeAdapter<Profile> typeAdapter(Gson gson) {
        return new AutoValue_Profile.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setName(Name name);
        public abstract Builder setEmail(String email);
        public abstract Builder setHexColor(String hexColor);
        public abstract Builder setDateOfBirth(Date dateOfBirth);
        public abstract Builder setBio(String bio);
        public abstract Builder setAvatar(String avatar);
        public abstract Profile build();
    }
}
