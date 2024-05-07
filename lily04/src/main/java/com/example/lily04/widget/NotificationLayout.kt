package com.example.lily04.widget

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.lily04.R

@SuppressLint("PrivateResource")
@Composable
fun NotificationLayout() {
    val context = LocalContext.current
    val bitmap = painterResource(id = androidx.core.R.drawable.notification_bg) as ImageBitmap

    Column(modifier = Modifier.padding(16.dp)) {
        Image(
            bitmap = bitmap,
            contentDescription = "Album Art",
            modifier = Modifier.size(120.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Song Title")
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* 上一曲逻辑 */ }) {
                Icon(Icons.Filled.KeyboardArrowLeft, contentDescription = "Previous")
            }
            IconButton(onClick = { /* 暂停逻辑 */ }) {
                Icon(Icons.Filled.Menu, contentDescription = "Pause")
            }
            IconButton(onClick = { /* 下一曲逻辑 */ }) {
                Icon(Icons.Filled.KeyboardArrowRight, contentDescription = "Next")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        LinearProgressIndicator(progress = 0.5f)
    }
}
