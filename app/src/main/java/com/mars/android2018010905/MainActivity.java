package com.mars.android2018010905;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    //宣告 notification_id = 21311 讓程式好閱讀
    final int notification_id = 21311;
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

        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //如果超過8.0才會跑這段
        if (Build.VERSION.SDK_INT >=26)
        {
            reChannel();
        }

    }
    //告訴編譯器以下程式用8.0的編譯
    @TargetApi(Build.VERSION_CODES.O)
    public  void  reChannel()
    {
        channelLove = new NotificationChannel(idLove,
                "Channel Love",
                NotificationManager.IMPORTANCE_HIGH);
        channelLove.setDescription("最重要的人");
        channelLove.enableLights(true);
        channelLove.enableVibration(true);
        nm.createNotificationChannel(channelLove);

    }

    @TargetApi(Build.VERSION_CODES.O)
    //忽略過時的API訊息
    @SuppressWarnings("deprecation")
    public void click1(View v)
    {
        Notification.Builder builder;
        if (Build.VERSION.SDK_INT >= 26)
        {
            builder = new Notification.Builder(MainActivity.this, idLove);
        }
        else
        {
            builder = new Notification.Builder(MainActivity.this);
        }


        builder.setContentTitle("測試");
        builder.setContentText("這是內容");

        //不同版本可能會出現圖形上的錯誤,不加這段會跟你說圖不對
        if (Build.VERSION.SDK_INT >= 26)
        {
            builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        }
        else
        {
            builder.setSmallIcon(R.mipmap.ic_launcher);
        }

        Notification notify = builder.build();
        //使用者點完之後會自動取消
        builder.setAutoCancel(true);
        //id:note的id
        nm.notify(notification_id, notify);

    }
    public void click2 (View V)
    {
        nm.cancel(notification_id);
    }
}
