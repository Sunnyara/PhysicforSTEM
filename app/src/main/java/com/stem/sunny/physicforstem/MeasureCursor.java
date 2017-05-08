package com.stem.sunny.physicforstem;

import android.database.Cursor;
import android.database.CursorWrapper;


import java.util.UUID;

import static com.stem.sunny.physicforstem.MeasureDBSchema.MeasureTable.Cols.*;


/**
 * Created by Sunnara on 2/16/2017.
 */

public class MeasureCursor extends CursorWrapper{
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public MeasureCursor(Cursor cursor) {
        super(cursor);
    }

    public Measurement getMeasurement() {
        String id = getString(getColumnIndex(ID));
        String title = getString(getColumnIndex(TITLE));
        double acceleration = getDouble(getColumnIndex(ACCELERATION));
        double time = getDouble(getColumnIndex(TIME));
        double velocity_init = getDouble(getColumnIndex(VELOCITY_INIT));
        double velocity_fin = getDouble(getColumnIndex(VELOCITY_FIN));
        Measurement m = new Measurement(Integer.parseInt(id));
        m.setTitle(title);
        m.setAcc(acceleration);
        m.setTime(time);
        m.setV0(velocity_init);
        m.setVf(velocity_fin);
        return m;
    }
}
