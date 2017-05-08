package com.stem.sunny.physicforstem;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.shapes.Shape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * @author Sunnara
 * @version 1.0
 *          This was created on 4/27/2017
 *          Description -
 */

public class DrawPoint extends View{

    private Paint p;
    private Canvas c;
    public DrawPoint(Context context) {
        super(context);
        p = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int radius = 25;
        p.setStyle(Paint.Style.FILL);
        p.setColor(getResources().getColor(R.color.babyblue_clicked));
        c = canvas;
        canvas.drawCircle(canvas.getWidth()/2,canvas.getHeight()/2,radius,p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        String tag = "";
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(tag, "ACTION_DOWN");
                Toast t = Toast.makeText(getContext(), "ACTION_DOWN", Toast.LENGTH_SHORT);
                t.show();
                break;

            case MotionEvent.ACTION_MOVE:
                Log.d(tag, "ACTION_MOVE");
                setX(event.getRawX() - 500);
                setY(event.getRawY() - 800);
                break;

            case MotionEvent.ACTION_UP:
                Log.d(tag, "ACTION_UP");
                t = Toast.makeText(getContext(), "ACTION_UP", Toast.LENGTH_SHORT);
                t.show();
                break;

            case MotionEvent.ACTION_CANCEL:
                Log.d(tag, "ACTION_CANCEL");
                t = Toast.makeText(getContext(), "ACTION_CANCEL", Toast.LENGTH_SHORT);
                t.show();
                break;

        }
        return true;
    }
}
