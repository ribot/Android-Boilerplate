package uk.co.ribot.androidboilerplate.data.local;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.Date;

import uk.co.ribot.androidboilerplate.data.model.Name;
import uk.co.ribot.androidboilerplate.data.model.Profile;

public class Db {

    public Db() { }

    public abstract static class RibotProfileTable {
        public static final String TABLE_NAME = "ribot_profile";

        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_FIRST_NAME = "first_name";
        public static final String COLUMN_LAST_NAME = "last_name";
        public static final String COLUMN_HEX_COLOR = "hex_color";
        public static final String COLUMN_DATE_OF_BIRTH = "date_of_birth";
        public static final String COLUMN_AVATAR = "avatar";
        public static final String COLUMN_BIO = "bio";

        public static final String CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_EMAIL + " TEXT PRIMARY KEY, " +
                        COLUMN_FIRST_NAME + " TEXT NOT NULL, " +
                        COLUMN_LAST_NAME + " TEXT NOT NULL, " +
                        COLUMN_HEX_COLOR + " TEXT NOT NULL, " +
                        COLUMN_DATE_OF_BIRTH + " INTEGER NOT NULL, " +
                        COLUMN_AVATAR + " TEXT, " +
                        COLUMN_BIO + " TEXT" +
                " ); ";

        public static ContentValues toContentValues(Profile profile) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_EMAIL, profile.email);
            values.put(COLUMN_FIRST_NAME, profile.name.first);
            values.put(COLUMN_LAST_NAME, profile.name.last);
            values.put(COLUMN_HEX_COLOR, profile.hexColor);
            values.put(COLUMN_DATE_OF_BIRTH, profile.dateOfBirth.getTime());
            values.put(COLUMN_AVATAR, profile.avatar);
            if (profile.bio != null) values.put(COLUMN_BIO, profile.bio);
            return values;
        }

        public static Profile parseCursor(Cursor cursor) {
            Profile profile = new Profile();
            profile.email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL));
            profile.name = new Name();
            profile.name.first = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FIRST_NAME));
            profile.name.last = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LAST_NAME));
            profile.hexColor = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HEX_COLOR));
            long dobTime = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_DATE_OF_BIRTH));
            profile.dateOfBirth = new Date(dobTime);
            profile.avatar = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AVATAR));
            profile.bio = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BIO));
            return profile;
        }
    }
}
