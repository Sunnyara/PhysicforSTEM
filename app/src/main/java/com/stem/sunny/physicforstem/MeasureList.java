package com.stem.sunny.physicforstem;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import static com.stem.sunny.physicforstem.MeasureDBSchema.MeasureTable.Cols.*;

/**
 * Created by Sunnara on 2/16/2017.
 */

public class MeasureList {
    public static MeasureList ml;
    private Context context;
    private SQLiteDatabase database;

    public static MeasureList get(Context c) {
        if(ml == null) {
            ml = new MeasureList(c);
        }
        return ml;
    }

    public MeasureList(Context c) {
        context = c.getApplicationContext();
        database = new MeasurementHelper(context)
                .getWritableDatabase();
    }

    public void addMeasurement(Measurement m) {
        ContentValues v = getContentValues(m);
    }

    private static ContentValues getContentValues(Measurement m) {
        ContentValues v = new ContentValues();
        v.put(ID, m.getId());
        v.put(TITLE, m.getTitle());
        v.put(ACCELERATION, m.getAcc());
        v.put(TIME, m.getTime());
        v.put(VELOCITY_INIT, m.getV0());
        v.put(VELOCITY_FIN, m.getVf());
        return v;
    }
}
