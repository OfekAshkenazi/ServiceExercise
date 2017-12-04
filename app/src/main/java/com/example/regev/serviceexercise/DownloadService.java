package com.example.regev.serviceexercise;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Regev on 02/12/2017.
 */

public class DownloadService extends Service implements DownloadThread.OnDownloadFinish{
    String[] songs=new String[]{"So High","Medicated","Rockstar","Congratulation"};
    DownloadThread thread;
    private boolean isRunning=false;

    public DownloadService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("Service Update","onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (isRunning==false){
            isRunning=true;
            thread=new DownloadThread(this,"DownloadThread");
            thread.start();
            for (String song:songs){
                Message message=Message.obtain();
                message.obj=song;
                thread.handler.sendMessage(message);
            }
            Message message=Message.obtain();
            message.obj=new Boolean(true);
            thread.handler.sendMessage(message);
            Log.v("Service Update","interrupt message sent");
        }
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Log.v("Service Update","onDestroy()");
        if (thread!=null){
            thread=null;
        }
        super.onDestroy();
    }

    @Override
    public void onDownloadFinish() {
        Log.v("Service Update","onDownloadFinish()");
        stopSelf();
    }
}
