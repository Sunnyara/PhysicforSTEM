package com.stem.sunny.physicforstem;

/**
 * @author Sunnara
 * @version 1.0
 *          This was created on 3/7/2017
 *          Description -
 */

public class CalculateFrame {


    public double[] catchDisplacement(TimeFrame tf1, TimeFrame tf2) {
        double tf1xPos, tf1yPos, tf2xPos, tf2yPos;
        tf1xPos = tf1.getxPos();
        tf1yPos = tf1.getyPos();
        tf2xPos = tf2.getxPos();
        tf2yPos = tf2.getyPos();

        double[] dis = new double[2];
        dis[0] = tf2xPos - tf1xPos;
        dis[1] = tf2yPos - tf1yPos;

        return dis;
    }
}
