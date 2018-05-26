package com.app.progressdialogproject;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    ProgressDialog progressBar;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = new ProgressDialog(this);
    }

    private int progress = 0;

    private Runnable rb = new Runnable() {
        @Override
        public void run() {
            if (progress > 100){
                progress = 0;
            }
            progressBar.setProgress(progress);
            progress++;
            handler.postDelayed(rb, 10);
        }
    };

    public void showDialog(View view) {
        progressBar.show();
        handler.postDelayed(rb, 50);
    }
}
