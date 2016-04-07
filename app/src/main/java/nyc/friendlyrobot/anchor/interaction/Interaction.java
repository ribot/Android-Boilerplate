package nyc.friendlyrobot.anchor.interaction;

import android.database.Cursor;

import com.squareup.sqlbrite.BriteDatabase;

import javax.inject.Inject;

import nyc.friendlyrobot.anchor.data.remote.Api;

public class Interaction {
    @Inject
    BriteDatabase db;

    @Inject
    Api api;


    public boolean recordExists(String TableName,
                                String dbfield, String fieldValue) {
        String Query = "Select * from " + TableName + " where " + dbfield + " = " + fieldValue;
        Cursor cursor = db.query(Query);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

}
