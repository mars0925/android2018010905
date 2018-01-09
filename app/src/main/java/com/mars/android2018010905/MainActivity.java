package com.mars.android2018010905;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    String idLove = "Love";
    //NotificationChannel是26 android 8.0以後才出的API
    //minSdkVersion要改成 26
    /*
    產生如果手機在8.0以前的都不能跑,所以要設定判斷版本
     */
    NotificationChannel channelLove;
    NotificationManager nm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        channelLove = new NotificationChannel(idLove,
                "Channel Love",
                NotificationManager.IMPORTANCE_HIGH);
        channelLove.setDescription("最重要的人");
        channelLove.enableLights(true);
        channelLove.enableVibration(true);

        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.createNotificationChannel(channelLove);

    }
    public void click1(View v)
    {
        Notification.Builder builder = new Notification.Builder(MainActivity.this, idLove);
        builder.setContentTitle("測試");
        builder.setContentText("這是內容");
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        Notification notify = builder.build();
        nm.notify(1, notify);

    }
}
