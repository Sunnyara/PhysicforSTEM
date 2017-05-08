package com.stem.sunny.physicforstem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author Sunnara
 * @version 1.0
 * Description - This class is an activitiy they will solve the missing variable and place it into
 *               the database for future usage.
 */
public class MotionSolver extends AppCompatActivity {

    private EditText acc, dis, v0, vf, time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.motion_activity);
        acc = (EditText) findViewById(R.id.acc_input2);
        dis = (EditText) findViewById(R.id.dist_input2);
        v0 = (EditText) findViewById(R.id.vel0_input2);
        vf = (EditText) findViewById(R.id.velf_input2);
        time = (EditText) findViewById(R.id.time_input2);

        Button solve = (Button) findViewById(R.id.motion_calc);
        solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double a,d,ve0,vef, t;
                a = Double.parseDouble(acc.getText().toString());
                d = Double.parseDouble(dis.getText().toString());
                ve0  =Double.parseDouble(v0.getText().toString());
                vef = Double.parseDouble(vf.getText().toString());
                t = Double.parseDouble(time.getText().toString());
                inputData(a,d,ve0,vef,t);
            }
        });
    }

    private void inputData(double acc, double dist, double v0, double vf, double time) {
        MeasurementHelper mHelper = new MeasurementHelper(this);
        Measurement m = new Measurement();
        m.setAcc(acc);
        m.setDistance(dist);
        m.setV0(v0);
        m.setVf(vf);
        m.setTime(time);
        PhysicCalculation pc;

        mHelper.insertData(m);

    }
}
