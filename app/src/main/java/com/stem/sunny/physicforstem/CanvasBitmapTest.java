package com.stem.sunny.physicforstem;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

/**
 * @author Sunnara
 * @date 3/2/2017
 * Description -
 */

public class CanvasBitmapTest extends View{

    public int w, h;
    private Bitmap bitmap;
    private Canvas canvas;
    private Path path;
    private Paint bmapPaint;
    Context c;
    private Paint circlePaint;
    private Paint circlePath;


    public CanvasBitmapTest(Context context) {
        super(context);
        c = context;
        path = new Path();
        bmapPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
