package com.example.lily04.pages
import AudioFile
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import android.content.Intent
import android.media.session.PlaybackState
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.lily04.model.MusicPlayerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicPlayerScreen(
    viewModel: MusicPlayerViewModel,
    playbackState: PlaybackState,
    onPlayPause: () -> Unit,
    onSeekForward: () -> Unit,
    onSeekBackward: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Music Player") }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(viewModel.audioFiles) { audioFile ->
                    AudioFileItem(
                        audioFile = audioFile,
                        onPlayClicked = { /* 播放音频文件 */ }
                    )
                }
            }

            PlayerControls(
                playbackState = playbackState,
                onPlayPause = onPlayPause,
                onSeekForward = onSeekForward,
                onSeekBackward = onSeekBackward
            )
        }
    }
}

@Composable
fun AudioFileItem(
    audioFile: AudioFile,
    onPlayClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onPlayClicked() }
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(audioFile.title)
        Text(audioFile.duration.toString())
    }
}

@Composable
fun PlayerControls(
    playbackState: PlaybackState,
    onPlayPause: () -> Unit,
    onSeekForward: () -> Unit,
    onSeekBackward: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        IconButton(onClick = onSeekBackward) {
            Icon(
                painter = painterResource(id =android.R.drawable.ic_media_previous),
                contentDescription = "Skip Previous"
            )
        }

        IconButton(onClick = onPlayPause) {
            Icon(
                painter = if (playbackState.state == PlaybackState.STATE_PLAYING) {
                    painterResource(id = android.R.drawable.ic_media_pause)
                } else {
                    painterResource(id = android.R.drawable.ic_media_play)
                },
                contentDescription = if (playbackState.state == PlaybackState.STATE_PLAYING) "Pause" else "Play"
            )
        }

        IconButton(onClick = onSeekForward) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_media_next),
                contentDescription = "Skip Next"
            )
        }
    }
}