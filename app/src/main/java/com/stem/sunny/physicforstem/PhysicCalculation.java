package com.stem.sunny.physicforstem;

import android.widget.Toast;

/**
 * Created by Sunnara on 1/24/2017.
 */

public class PhysicCalculation {
    private boolean a, vI, vF, xD;
    private double accel, velInit, velFin, disp;
    private final double gravity = 9.8;


    /**
     * There are formulas used in the kinematic formula
     * We contain the variables
     *      Δx = Displacement (xD)
     *      t = time interval (t)
     *      v0 = Initial Velocity
     *      vF = Final Velocity
     *      a = Acceleration (may be gravity)
     */
    public PhysicCalculation() {
    }


    public boolean hasTooMuchMissingValue(Measurement m) {
        if(m.getAcc() == 0) {

        }
        if(!a)
            if(!xD)
                return true;

        if(!vI)
            if(!xD)
            return true;
        return false;
    }



}
