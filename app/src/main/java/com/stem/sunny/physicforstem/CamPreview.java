package com.stem.sunny.physicforstem;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * @author Sunnara
 * @version 1.0
 * Description -
 */

public class CamPreview extends SurfaceView implements SurfaceHolder.Callback{
    private static final String TAG = "" ;
    private SurfaceHolder sh;
    private Camera cam;

    public CamPreview(Context context, Camera camera) {
        super(context);
        cam = camera;

        sh = getHolder();
        sh.addCallback(this);
        sh.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            cam.setPreviewDisplay(holder);
            cam.setDisplayOrientation(90);
            cam.startPreview();
        } catch (IOException e) {
            Log.d(TAG, "Error setting camera preview: " + e.getMessage());
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if(sh.getSurface() == null) {
            return;
        }

        try {
            cam.stopPreview();
        } catch (Exception e) {}

            surfaceCreated(sh);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        cam.stopPreview();
    }
}
