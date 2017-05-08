package com.stem.sunny.physicforstem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.stem.sunny.physicforstem.MESSAGE";
    private ImageView mCalcButton, mCameraButton, u1, u2, u3, u4;
    private RelativeLayout mAboutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mCalcButton = (ImageView) findViewById(R.id.calcbutton);
        mCalcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CalcActivity.class));
            }
        });

        mCameraButton = (ImageView) findViewById(R.id.camerabutton);
        mCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, MediaList.class));
            }
        });

        mAboutButton = (RelativeLayout) findViewById(R.id.about_click);
        mAboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
            }
        });

        final Toast t = Toast.makeText(this,"More Features to be added!",Toast.LENGTH_SHORT);
        final int[] counter = {0};

        u1 = (ImageView) findViewById(R.id.u1);
        u2 = (ImageView) findViewById(R.id.u2);
        u3 = (ImageView) findViewById(R.id.u3);
        u4 = (ImageView) findViewById(R.id.u4);
        u1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t.show();
                counter[0]++;
                if (counter[0] >= 10) {
                    t.setText("Hey, Stop Clicking");
                }
            }
        });
        u2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t.show();
                counter[0]++;
                if (counter[0] >= 10) {
                    t.setText("Hey, Stop Clicking");
                }
            }
        });
        u3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t.show();
                counter[0]++;
                if (counter[0] >= 10) {
                    t.setText("Hey, Stop Clicking");
                }
            }
        });
        u3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t.show();
                counter[0]++;
                if (counter[0] >= 10) {
                    t.setText("Hey, Stop Clicking");
                }
            }
        });
    }

}
