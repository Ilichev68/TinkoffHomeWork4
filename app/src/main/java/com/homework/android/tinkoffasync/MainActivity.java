package com.homework.android.tinkoffasync;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements ThreadDownloadManager.DownloadListener, ThreadShowManager.ShowListener {

    private TextView textView;
    private static boolean isNotFirstDownload;
    private static boolean isIsNotFirstShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isNotFirstDownload) ThreadDownloadManager.registerListener(MainActivity.this);
        if (isIsNotFirstShow) ThreadShowManager.registerListener(MainActivity.this);

        textView = (TextView) findViewById(R.id.result_array);
        Button btnDownload = (Button) findViewById(R.id.download);
        Button btnShow = (Button) findViewById(R.id.show_array);


        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isNotFirstDownload = true;
                ThreadDownloadManager.registerListener(MainActivity.this);
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isIsNotFirstShow = true;
                ThreadShowManager.registerListener(MainActivity.this);
            }
        });

    }

    @Override
    public void onDownloadUpdate(final String value) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(value);
            }
        });
    }


    @Override
    public void onShowUpdate(final String value) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(value);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        ThreadDownloadManager.isStopped = true;
        ThreadShowManager.isStopped = true;
    }
}
