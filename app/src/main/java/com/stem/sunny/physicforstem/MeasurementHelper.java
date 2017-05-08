package com.stem.sunny.physicforstem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.stem.sunny.physicforstem.MeasureDBSchema.MeasureTable;

import java.util.ArrayList;

import static com.stem.sunny.physicforstem.MeasureDBSchema.MeasureTable.Cols.*;
import static com.stem.sunny.physicforstem.MeasureDBSchema.MeasureTable.*;

/**
 * Created by Sunnara on 2/16/2017.
 */

public class MeasurementHelper extends SQLiteOpenHelper{

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "MeasurementTable.db";

    public MeasurementHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        MeasureDBSchema.onCreate(db);
    }

    public void insertData(Measurement m) {
        ContentValues c = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        c.put(ACCELERATION, m.getAcc());
        c.put(VELOCITY_INIT, m.getV0());
        c.put(VELOCITY_FIN, m.getVf());
        c.put(TIME, m.getTime());
        db.insert(TABLE_NAME, null, c);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.setVersion(newVersion);
    }

    public ArrayList<Measurement> getAllMeasurements() {
        ArrayList<Measurement> a = new ArrayList<Measurement>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * from " + TABLE_NAME, null);
        c.moveToFirst();
        while(!c.isAfterLast()) {
            Measurement m = fillMeasurement(c);
            a.add(m);
            c.moveToNext();
        }
        c.close();
        return a;
    }

    public Measurement fillMeasurement(Cursor cursor) {
        Measurement m = new Measurement();
        m.setId(cursor.getInt(0));
        m.setTitle((cursor.getString(1)));
        m.setAcc(cursor.getDouble(2));
        m.setTime(cursor.getDouble(3));
        m.setV0((cursor.getDouble(4)));
        m.setVf((cursor.getDouble(5)));
        return m;
    }
}
