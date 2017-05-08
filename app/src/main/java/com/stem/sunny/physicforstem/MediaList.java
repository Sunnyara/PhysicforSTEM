package com.stem.sunny.physicforstem;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * @author Sunnara
 * @version 1.0
 * Description - displays current items in the database and has the option to add more either using
 *              cameras or inputing the values manually
 *
 */
public class MediaList extends AppCompatActivity{

    private Context context;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_list);
        context = this;
        MeasurementHelper mh = new MeasurementHelper(this);

        ArrayList<Measurement>  al = mh.getAllMeasurements();

        MeasureAdapter ma = new MeasureAdapter(al);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        RecyclerView rc = (RecyclerView) findViewById(R.id.measure_list);
        rc.hasFixedSize();
        rc.setLayoutManager(lm);
        rc.setAdapter(ma);
        ImageButton removeButton = (ImageButton) findViewById(R.id.remove_mlist);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }});
        ImageButton addButton = (ImageButton) findViewById(R.id.add_mlist);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog();
            }
        });
    }

    public void createDialog() {
        final Dialog d = new Dialog(context);
        d.setContentView(R.layout.media_list_add_dialog);

        TextView inputData = (TextView) d.findViewById(R.id.input_data);
        inputData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MediaList.this, MotionSolver.class));
            }
        });
        TextView useCamera = (TextView) d.findViewById(R.id.use_camera);
        useCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MediaList.this, CameraActivity.class));
            }
        });

        d.setTitle("Select Method");
        d.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Dialog d = new Dialog(context);
        d.dismiss();
    }
}
