package com.stem.sunny.physicforstem;
import android.app.Activity;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.CamcorderProfile;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Sunnara on 1/24/2017.
 */

public class CameraActivity2 extends Activity implements SurfaceHolder.Callback {

    private Camera camera;
    private RelativeLayout pointLayout, pointDot;
    private TextView timer, pointDist;
    //private CamPreview cp;
    private MediaRecorder mediaRecorder;

    private File currentFile;
    private MediaPlayer mp;
    private SurfaceView sv;
    private SurfaceHolder sh;
    private Button record, meter, point, done;
    private ImageButton aPoint, rPoint;
    private CountDownTimer cdt;
    private View meterLine, dotPoint;
    private ArrayList<DrawPoint> dp;

    private boolean recording;
    private final String tag = "Video";

    private float pxPerM;


    private int distance = 0;
    private Handler h = new Handler();
    Runnable r = new Runnable() {
        @Override
        public void run() {
            if(dp.size() != 2) {
                return;
            }
            distance = Math.abs(dp.get(0).getTop() - dp.get(1).getTop());
            dp.get(0).getTop();
            pointDist.setText("Distance:"+ distance + "m");
            h.postDelayed(this,0);
        }


    };



    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] STORAGE_PERMISSION = {
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
};

    private boolean permissionToRecordAccepted = false;
    private boolean permissionToWriteAccepted = false;
    private String [] permissions = {"android.permission.RECORD_AUDIO", "android.permission.WRITE_EXTERNAL_STORAGE"};



    public CameraActivity2() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_surface);


        dp = new ArrayList<>();
        pxPerM = 0;

        sv = (SurfaceView) findViewById(R.id.camera_view);
        sh = sv.getHolder();

        sh.addCallback(this);

        sh.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        //final RelativeLayout rl = (RelativeLayout) findViewById(R.id.layout_size);

        pointLayout = (RelativeLayout) findViewById(R.id.point_canvas);
        pointDot = (RelativeLayout) findViewById(R.id.point_dot);


        final boolean[] meterdone = {false};
        final boolean[] pointdone = {false};

        final Toast[] recordToast = new Toast[2];
        recordToast[0] = Toast.makeText(getApplicationContext(), "Recording started", Toast.LENGTH_SHORT);
        recordToast[1] = Toast.makeText(getApplicationContext(), "Recording stopped", Toast.LENGTH_SHORT);

        timer = (TextView) findViewById(R.id.timer);

        mp = new MediaPlayer();
        record = (Button) findViewById(R.id.record_cam);
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meterLine.setVisibility(View.INVISIBLE);
                meterdone[0] = false;
                pointLayout.setVisibility(View.INVISIBLE);
                pointdone[0] = false;
                if(recording) {
                    recording = false;
                    mediaRecorder.stop();
                    timer.setVisibility(View.INVISIBLE);
                    cdt.onFinish();
                    record.setText("REC");
                    recordToast[1].show();
                } else {
                    videoPrepare();
                    mediaRecorder.start();
                    timer.setVisibility(View.VISIBLE);
                    startTimer();
                    recording = true;
                    record.setText("STOP");
                    recordToast[0].show();
                    Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            record.setText("REC");
                            recordToast[1].show();
                            recording = false;
                            timer.setVisibility(View.INVISIBLE);
                            cdt.onFinish();
                        }
                    }, 10000);
                    playVideo();
                }
            }
        });

        meterLine = (View) findViewById(R.id.measure_line);
        //meterLine.setVisibility(View.INVISIBLE);


        meter = (Button) findViewById(R.id.meter_cam);
        meter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pointLayout.setVisibility(View.INVISIBLE);
                pointdone[0] = false;
                if(meterdone[0] == false) {
                    meterLine.setVisibility(View.VISIBLE);
                    pxPerM = meterLine.getHeight();
                    final Toast[] temp = {Toast.makeText(CameraActivity2.this, "Current Height: " + pxPerM, Toast.LENGTH_SHORT)};
                    temp[0].show();
                    meterLine.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            float y_size = 0;
                            float yPlaceHolder = 0;
                            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                                case MotionEvent.ACTION_DOWN:
                                    Log.d(tag, "ACTION_DOWN");
                                    Toast t = Toast.makeText(CameraActivity2.this, "ACTION_DOWN", Toast.LENGTH_SHORT);
                                    t.show();
                                    //yPlaceHolder = event.getRawY();

                                    break;

                                case MotionEvent.ACTION_POINTER_DOWN:
                                    t = Toast.makeText(CameraActivity2.this, "ACTION_POINTER_DOWN", Toast.LENGTH_SHORT);
                                    t.show();
                                    break;


                                case MotionEvent.ACTION_POINTER_UP:
                                    t = Toast.makeText(CameraActivity2.this, "ACTION_POINTER_UP", Toast.LENGTH_SHORT);
                                    t.show();
                                    break;

                                case MotionEvent.ACTION_MOVE:
                                    Log.d(tag, "ACTION_MOVE");
                                    meterLine.setX(event.getRawX());
                                    meterLine.setY(event.getRawY() - 300);

                                    if (event.getPointerCount() == 2) {
                                        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) meterLine.getLayoutParams();
                                        y_size += Math.abs(event.getY(0) - event.getY(1));
                                        lp.height = (int) y_size;
                                        meterLine.setLayoutParams(lp);
                                    }
                                    //t = Toast.makeText(CameraActivity.this,"ACTION_MOVE",Toast.LENGTH_SHORT);
                                    //t.show();
                                    break;

                                case MotionEvent.ACTION_UP:
                                    Log.d(tag, "ACTION_UP");
                                    t = Toast.makeText(CameraActivity2.this, "ACTION_UP", Toast.LENGTH_SHORT);
                                    pxPerM = meterLine.getHeight();
                                    temp[0] = Toast.makeText(CameraActivity2.this, "Current height: " + pxPerM, Toast.LENGTH_SHORT);
                                    temp[0].show();
                                    t.show();
                                    break;

                                case MotionEvent.ACTION_CANCEL:
                                    Log.d(tag, "ACTION_CANCEL");
                                    t = Toast.makeText(CameraActivity2.this, "ACTION_CANCEL", Toast.LENGTH_SHORT);
                                    t.show();
                                    break;

                            }
                            return true;
                        }
                    });
                    meterdone[0] = true;
                }
                else
                {
                    meterLine.setVisibility(View.INVISIBLE);
                    meterdone[0] = false;
                }

            }
        });

        point = (Button) findViewById(R.id.point_cam);
        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meterLine.setVisibility(View.INVISIBLE);
                meterdone[0] = false;
                if(!pointdone[0]) {
                    pointdone[0] = true;
                    pointLayout.setVisibility(View.VISIBLE);

                }
                else
                {
                    pointdone[0] = false;
                    pointLayout.setVisibility(View.INVISIBLE);
                }
            }
        });

        dotPoint = (View) findViewById(R.id.point_dot);
        pointDist = (TextView) findViewById(R.id.point_distance);

        aPoint = (ImageButton) findViewById(R.id.add_point);
        aPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dp.add(new DrawPoint(CameraActivity2.this));
                pointLayout.addView(dp.get(dp.size()-1));
                //pointLayout.addTouchables(dp.get(0).getTouchables());
                if(dp.size() == 2) {
                    h.postDelayed(r,0);
                }

            }
        });





        rPoint = (ImageButton) findViewById(R.id.remove_point);
        rPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dp.size() == 0) {
                    return;
                }
                pointLayout.removeView(dp.get(dp.size()-1));
                dp.remove(dp.get(dp.size()-1));

            }
        });


        done = (Button) findViewById(R.id.done_cam);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void playVideo() {
        releaseMediaRecorder();
        try {
            mp.setDataSource(getApplicationContext(), Uri.fromFile(currentFile));
            mp.setSurface(sh.getSurface());
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mp.prepare();
            mp.start();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startTimer() {
        cdt = new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000;
                timer.setText(String.format("00:%02d", seconds));
            }

            @Override
            public void onFinish() {
                timer.setText("00:00");
            }
        };
        cdt.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseMediaRecorder();
        releaseCamera();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(recording) {
            mediaRecorder.stop();
            //preview.removeView(cp); was a relativeLayout

        }
    }

    private void videoPrepare() {
        mediaRecorder = new MediaRecorder();
        camera.unlock();
        mediaRecorder.setCamera(camera);

        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);

        CamcorderProfile cm = CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH);
        cm.videoFrameHeight = 720;
        cm.videoFrameWidth = 1280;

        mediaRecorder.setProfile(cm);


        File f;
        int counter = 0;
        Boolean hasSd = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        File dir;

        if(hasSd) {
            dir = new File(Environment.getExternalStorageDirectory() + "/CamCapture/");
        } else {
            dir = new File(getFilesDir() + "/CamCapture/");
        }
        dir.mkdirs();



        while (new File(dir.getPath() + "/video" + counter + ".mp4").exists()) {
            counter++;
        }
        f = new File(dir.toString() + "/video" + counter + ".mp4");
        currentFile = f;
        mediaRecorder.setOutputFile(f.getPath());
        mediaRecorder.setMaxDuration(10000);
        //mediaRecorder.setCaptureRate(30);
        mediaRecorder.setPreviewDisplay(sh.getSurface());

        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            Log.e(tag, "Video Error");
        }
    }

    public static Camera getCameraInstance() {
        Camera c = null;
        try {c = Camera.open();}
        catch (Exception e) {}
        return c;
    }

    public void releaseMediaRecorder() {
        if(mediaRecorder != null) {
            mediaRecorder.reset();
            mediaRecorder.release();
            mediaRecorder = null;
            camera.lock();
        }
    }

    public void releaseCamera() {
        if(camera != null) {
            camera.release();
            camera = null;
        }
    }

    public void cameraSetup() {
        camera = getCameraInstance();
        Camera.Parameters param = camera.getParameters();
        CamcorderProfile cm = CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH);
        cm.videoFrameHeight = 720;
        cm.videoFrameWidth = 1280;
        param.setPreviewSize(cm.videoFrameWidth,cm.videoFrameHeight);
        camera.setParameters(param);
        camera.setDisplayOrientation(90);
        try {
            camera.setPreviewDisplay(sh);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        cameraSetup();
        camera.startPreview();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
