package com.stem.sunny.physicforstem;

/**
 * Created by Sunnara on 2/14/2017.
 */

public class Measurement {
    private int id;
    private String title;
    private double v0, vf, acc, time, dist;

    public Measurement(int id) {
        setId(id);
    }
    public Measurement() {}

    public Measurement(String title, double v0, double vf, double acc, double time) {
        setTitle(title);
        setV0(v0);
        setVf(vf);
        setAcc(acc);
        setTime(time);
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDistance(double dist) {
        this.dist = dist;
    }

    public double getDistance() {
        return dist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getV0() {
        return v0;
    }

    public void setV0(double v0) {
        this.v0 = v0;
    }

    public double getVf() {
        return vf;
    }

    public void setVf(double vf) {
        this.vf = vf;
    }

    public double getAcc() {
        return acc;
    }

    public void setAcc(double acc) {
        this.acc = acc;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }
}
