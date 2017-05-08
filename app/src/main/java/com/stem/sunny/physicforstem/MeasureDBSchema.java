package com.stem.sunny.physicforstem;

import android.database.sqlite.SQLiteDatabase;

import static com.stem.sunny.physicforstem.MeasureDBSchema.MeasureTable.Cols.*;
import static com.stem.sunny.physicforstem.MeasureDBSchema.MeasureTable.*;

/**
 * Created by Sunnara on 2/16/2017.
 */

public class MeasureDBSchema {

    private MeasureDBSchema() {}

    public static final class MeasureTable {
        public static final String TABLE_NAME = "objects";


        public static final class Cols {
            public static final String ID = "id";
            public static final String TITLE = "title";
            public static final String ACCELERATION = "acceleration";
            public static final String VELOCITY_INIT = "velocity_init";
            public static final String VELOCITY_FIN = "velocity_fin";
            public static final String TIME = "time";

        }
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID + " INTEGER PRIMARY KEY," +
                    TITLE + " TEXT," +
                    ACCELERATION + " REAL," +
                    TIME + " REAL," +
                    VELOCITY_INIT + " REAL," +
                    VELOCITY_FIN + " REAL" +
                    ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
}
