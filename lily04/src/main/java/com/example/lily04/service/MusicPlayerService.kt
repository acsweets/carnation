package com.example.lily04.service

import AudioFile
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.app.Service.START_NOT_STICKY
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.media.session.PlaybackState
import android.media.session.PlaybackState.ACTION_PAUSE
import android.media.session.PlaybackState.ACTION_PLAY
import android.net.Uri
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.ServiceCompat.startForeground
import androidx.core.content.ContextCompat.getString
import androidx.core.content.ContextCompat.getSystemService
import com.example.lily04.R

class MusicPlayerService : Service() {
    private val player: MediaPlayer = MediaPlayer()
    private var currentAudioFile: AudioFile? = null
    private val notificationManager: NotificationManager by lazy {
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    companion object {
        private const val CHANNEL_ID = "music_player_channel"
        private const val NOTIFICATION_ID = 1
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        setupMediaPlayer()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_PLAY.toString() -> {
                val audioFileUri = intent.data ?: return START_NOT_STICKY
                startForeground(NOTIFICATION_ID, createNotification())
                playAudioFile(audioFileUri)
            }

            ACTION_PAUSE.toString() -> pauseAudioFile()
//            ACTION_NEXT -> skipToNextAudioFile()
//            ACTION_PREV -> skipToPreviousAudioFile()
            else -> return START_NOT_STICKY
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Music Player",
                NotificationManager.IMPORTANCE_LOW
            )
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun setupMediaPlayer() {
        player.setOnPreparedListener {
            updateNotification(PlaybackState.STATE_PLAYING)
            player.start()
        }
        player.setOnCompletionListener { skipToNextAudioFile() }
    }

    private fun playAudioFile(audioFileUri: Uri) {
        player.reset()
        player.setDataSource(this, audioFileUri)
        player.prepareAsync()
    }

    private fun pauseAudioFile() {
        if (player.isPlaying) {
            player.pause()
            updateNotification(PlaybackState.STATE_PAUSED)
        }
    }

    private fun updateNotification(paused: Any) {

    }

    private fun skipToNextAudioFile() {
        // TODO: Implement skipping to next audio file
    }

    private fun skipToPreviousAudioFile() {
        // TODO: Implement skipping to previous audio file
    }

    private fun createNotification(): Notification {
        return Notification()
    }
}