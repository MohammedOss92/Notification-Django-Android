package com.example.mynotification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.os.IResultReceiver;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FirebaseMessagingservice extends FirebaseMessagingService  {
    NotificationCompat.Builder notifiaction;
    Bitmap bitmap;
    String id="Default";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage)
    {
//        Intent intent=new Intent(this,MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
//        String chanelId = "Default";
//        notifiaction=new NotificationCompat.Builder(this,chanelId)
//
//
//
//                .setSmallIcon(R.drawable.ic_launcher_background)
//                .setContentTitle(remoteMessage.getNotification().getTitle())
//                .setContentText(remoteMessage.getNotification().getBody()).setAutoCancel(true).setContentIntent(pendingIntent);
//
//        NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel(chanelId,"Default chanel",
//                    NotificationManager.IMPORTANCE_DEFAULT);
//            notificationManager.createNotificationChannel(channel);
//        }
//
//        notificationManager.notify(0,notifiaction.build());
//        super.onMessageReceived(remoteMessage);

        
        String title = remoteMessage.getNotification().getTitle();
        String body = remoteMessage.getNotification().getBody();
        String imgurl = remoteMessage.getData().get("image");
        String tag = remoteMessage.getData().get("tag");


        bitmap=getbitmap(imgurl);



        getnotifiacation(bitmap,title,body,imgurl);
    }

    @Override
    public void onNewToken(String token) {

    }


    private void getnotifiacation(Bitmap bitmap, String title, String body, String imgurl)
    {

        Intent intent=new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        notifiaction=new NotificationCompat.Builder(this,id)
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentText(body)
                .setLargeIcon(getbitmap(imgurl))
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap))
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(id,"notifiaction",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0,notifiaction.build());

    }



    public static Bitmap getbitmap(String imgurl)
    {
        try
        {
            URL url=new URL(imgurl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream inputStream=connection.getInputStream();
            Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
            return  bitmap;

        }catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }

    }
}
