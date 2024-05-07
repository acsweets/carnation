//package com.example.lily04.widget
//
//import android.app.Notification
//import android.content.Context
//import android.widget.RemoteViews
//import androidx.core.app.NotificationCompat
//import com.example.lily04.R
//
//fun buildNotification(context: Context): Notification {
//    val notificationLayout = RemoteViewsHelper(context).buildNotificationLayout()
//
//    val notification = NotificationCompat.Builder(context, CHANNEL_ID)
//        .setSmallIcon(androidx.core.R.drawable.notification_icon_background)
//        .setContent(notificationLayout)
//        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//        .build()
//
//    // 如果你需要添加点击通知时的操作，可以在此处添加 PendingIntent
//
//    return notification
//}
//class RemoteViewsHelper(private val context: Context) {
//    fun buildNotificationLayout(): RemoteViews {
//        val remoteViews = RemoteViews(context.packageName, R.layout.notification_layout)
//
//        // 设置图片
//        remoteViews.setImageViewResource(R.id.notification_image, R.drawable.album_art)
//
//        // 设置标题
//        remoteViews.setTextViewText(R.id.notification_title, "Song Title")
//
//        // 设置上一曲、下一曲、暂停按钮的点击事件
//        val prevIntent = Intent(context, YourPreviousSongService::class.java)
//        val prevPendingIntent = PendingIntent.getService(context, 0, prevIntent, 0)
//        remoteViews.setOnClickPendingIntent(R.id.notification_prev, prevPendingIntent)
//
//        val nextIntent = Intent(context, YourNextSongService::class.java)
//        val nextPendingIntent = PendingIntent.getService(context, 0, nextIntent, 0)
//        remoteViews.setOnClickPendingIntent(R.id.notification_next, nextPendingIntent)
//
//        val pauseIntent = Intent(context, YourPauseService::class.java)
//        val pausePendingIntent = PendingIntent.getService(context, 0, pauseIntent, 0)
//        remoteViews.setOnClickPendingIntent(R.id.notification_pause, pausePendingIntent)
//
//        return remoteViews
//    }
//}
