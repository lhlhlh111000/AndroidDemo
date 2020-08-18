package com.pig.android.demo.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.pig.android.demo.R
import kotlinx.android.synthetic.main.activity_notification.*

/**
 * Title:
 * Description:
 * Copyright © 2001-2020 17173. All rights reserved.
 *
 * @author cqt
 * @version 2020/8/18
 */
class NotificationActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        btn_notification_1.setOnClickListener {
            notification1()
        }
        btn_notification_2.setOnClickListener {
            notification2()
        }
        btn_notification_3.setOnClickListener {
            notification3()
        }
    }

    private fun notification1() {
        val intent = this.packageManager.getLaunchIntentForPackage(packageName)
        val pi = PendingIntent.getActivity(this, 0, intent, 0)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notification: NotificationCompat.Builder = NotificationCompat.Builder(this, "default_channel")
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setContentTitle("一个短文本通知")
            .setContentText("只会显示一行，\n太长就会省略咯,只会显示一行，\n" +
                    "太长就会省略咯,只会显示一行，\\n\" +\n" +
                    "                    \"太长就会省略咯,只会显示一行，\\n\" +\n" +
                    "                    \"太长就会省略咯,只会显示一行，\\n\" +\n" +
                    "                    \"太长就会省略咯,只会显示一行，\\n\" +\n" +
                    "                    \"太长就会省略咯,只会显示一行，\\n\" +\n" +
                    "                    \"太长就会省略咯,只会显示一行，\\n\" +\n" +
                    "                    \"太长就会省略咯")
            .setAutoCancel(true)
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
            .setContentIntent(pi)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(packageName, "notification",
                NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        notification.setChannelId(packageName)


        manager.notify(1, notification.build())
    }

    private fun notification2() {
        val intent = this.packageManager.getLaunchIntentForPackage(packageName)
        val pi = PendingIntent.getActivity(this, 0, intent, 0)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notification: NotificationCompat.Builder = NotificationCompat.Builder(this, "default_channel")
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setContentTitle("一个长文本通知")
            .setStyle(
                NotificationCompat.BigTextStyle().bigText("这是一个很长很长很长的消息啊，这是一个很长很长很长的消息啊，" +
                    "这是一个很长很长很长的消息啊，" +
                    "这是一个很长很长很长的消息啊，" +
                    "这是一个很长很长很长的消息啊，" +
                    "这是一个很长很长很长的消息啊，" +
                    "这是一个很长很长很长的消息啊，" +
                    "这是一个很长很长很长的消息啊，" +
                    "这是一个很长很长很长的消息啊，" +
                    "这是一个很长很长很长的消息啊，"))
            .setAutoCancel(true)
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
            .setContentIntent(pi)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(packageName, "notification",
                NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        notification.setChannelId(packageName)


        manager.notify(1, notification.build())
    }

    private fun notification3() {
        val intent = this.packageManager.getLaunchIntentForPackage(packageName)
        val pi = PendingIntent.getActivity(this, 0, intent, 0)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notification: NotificationCompat.Builder = NotificationCompat.Builder(this, "default_channel")
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setContentTitle("一个大图通知")
            .setStyle(
                NotificationCompat.BigPictureStyle().bigPicture(
                BitmapFactory.decodeResource(resources, R.mipmap.big_img)
            ))
            .setAutoCancel(true)
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
            .setContentIntent(pi)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(packageName, "notification",
                NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        notification.setChannelId(packageName)


        manager.notify(1, notification.build())
    }
}