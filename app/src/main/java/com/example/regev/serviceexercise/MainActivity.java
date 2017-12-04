package com.example.regev.serviceexercise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.startBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("Service Update","startServiceCalled");
                startService(new Intent(MainActivity.this,DownloadService.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        Log.v("Service Update","stopServiceCalled");
        stopService(new Intent(MainActivity.this,DownloadService.class));
        super.onDestroy();
    }
}
