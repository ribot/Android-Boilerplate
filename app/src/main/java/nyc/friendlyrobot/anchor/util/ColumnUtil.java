package nyc.friendlyrobot.anchor.util;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.common.base.Optional;
import com.squareup.sqldelight.ColumnAdapter;

public class ColumnUtil {
    public static final ColumnAdapter<Integer> INTEGER_COLUMN_ADAPTER = new ColumnAdapter<Integer>() {
        @Override
        public Integer map(Cursor cursor, int columnIndex) {
            return cursor.getInt(columnIndex);
        }

        @Override
        public void marshal(ContentValues values, String key, Integer value) {
            values.put(key, value);
        }
    };

    public static final ColumnAdapter<Long> LONG_COLUMN_ADAPTER = new ColumnAdapter<Long>() {
        @Override
        public Long map(Cursor cursor, int columnIndex) {
            return cursor.getLong(columnIndex);
        }

        @Override
        public void marshal(ContentValues values, String key, Long value) {
            values.put(key, value);
        }
    };

    public static final ColumnAdapter<Optional<Long>> OPTIONAL_LONG_COLUMN_ADAPTER = new ColumnAdapter<Optional<Long>>() {
        @Override
        public Optional<Long> map(Cursor cursor, int columnIndex) {
            return Optional.fromNullable(cursor.getLong(columnIndex));
        }

        @Override
        public void marshal(ContentValues values, String key, Optional<Long> value) {
            values.put(key,value.orNull());
        }
    };
    public static final ColumnAdapter<Optional<String>> OPTIONAL_STRING_COLUMN_ADAPTER = new ColumnAdapter<Optional<String>>() {
        @Override
        public Optional<String> map(Cursor cursor, int columnIndex) {
            return Optional.fromNullable(cursor.getString(columnIndex));
        }

        @Override
        public void marshal(ContentValues values, String key, Optional<String> value) {
            values.put(key,value.orNull());
        }
    };
    public static final ColumnAdapter<Optional<Integer>> OPTIONAL_INTEGER_COLUMN_ADAPTER = new ColumnAdapter<Optional<Integer>>() {
        @Override
        public Optional<Integer> map(Cursor cursor, int columnIndex) {
            return Optional.fromNullable(cursor.getInt(columnIndex));
        }

        @Override
        public void marshal(ContentValues values, String key, Optional<Integer> value) {
            values.put(key,value.orNull());
        }
    };
}
