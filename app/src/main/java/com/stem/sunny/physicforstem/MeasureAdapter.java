package com.stem.sunny.physicforstem;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Sunnara on 2/14/2017.
 */

public class MeasureAdapter extends RecyclerView.Adapter<MeasureAdapter.MeasureHolder> {



    private ArrayList<Measurement> al;

    public MeasureAdapter(ArrayList<Measurement> al) {
        this.al = al;
    }

    @Override
    public MeasureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.measure_fragment,parent,false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return new MeasureHolder(v);
    }

    @Override
    public void onBindViewHolder(MeasureHolder holder, int position) {
        Measurement measurement = al.get(position);

        holder.title.setText(measurement.getTitle());
        holder.v0.setText("" + measurement.getV0() + " m/s");
        holder.vf.setText("" + measurement.getVf() + " m/s");
        holder.acc.setText("" + measurement.getAcc() + " m/s^2");
        holder.time.setText(""+ measurement.getTime() + " s");
    }


    @Override
    public int getItemCount() {
        return al.size();
    }






    public class MeasureHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView title, v0, vf, acc, time;

        public MeasureHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.measure_title);
            v0 = (TextView) itemView.findViewById(R.id.velocity_init);
            vf = (TextView) itemView.findViewById(R.id.velocity_fin);
            acc = (TextView) itemView.findViewById(R.id.acc_input);
            time = (TextView) itemView.findViewById(R.id.time_input);
        }


        @Override
        public void onClick(View v) {
        }
    }
}
