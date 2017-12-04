package com.example.regev.serviceexercise;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/**
 * Created by Regev on 02/12/2017.
 */

public class DownloadThread extends Thread {
    DownloadHandler handler;
    OnDownloadFinish listener;

    public DownloadThread(OnDownloadFinish listener, String downloadThread) {
        super(downloadThread);
        this.listener=listener;
        handler=new DownloadHandler(this);
    }


    @Override
    public void run() {
        while (!isInterrupted()){

        }
        listener.onDownloadFinish();
    }

    @Override
    public void interrupt() {
        super.interrupt();
        Log.v("Thread Update","interrupt()");
    }

    public static class DownloadHandler extends android.os.Handler{
        DownloadThread thread;

        public DownloadHandler(DownloadThread thread) {
            super();
            this.thread = thread;
        }

        @Override
        public void handleMessage(Message msg) {
            if (msg.obj instanceof Boolean){
                Log.v("Thread Update","interrupt message received");
                Boolean flag= (Boolean) msg.obj;
                if (flag==true){
                    thread.interrupt();
                }
            }
            else{
                try {
                    downloadSong(msg.obj.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

        void downloadSong(String song) throws InterruptedException {
            Log.d("Download Update:  ","Started Download "+song);

            for (int i = 10; i >0 ; i--) {
                Thread.sleep(1000);
                Log.d("Download Update:  ",i+"");
            }
        }
    }
    public interface OnDownloadFinish{
        void onDownloadFinish();
    }
}
