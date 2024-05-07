package com.example.lily04.widget

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedText() {
    var offsetX by remember { mutableStateOf(0f) }

    // 使用 animateFloatAsState 创建一个动画状态
    val animatedOffsetX by animateFloatAsState(
        targetValue = offsetX, // 动画的目标值
        animationSpec = tween(durationMillis = 1000), label = "" // 动画规格
    )

    // 每次动画完成后更新 offsetX 的值
    LaunchedEffect(animatedOffsetX) {
        offsetX = if (offsetX == 0f) 300f else 0f
    }

    // 使用 Column 来垂直排列文本和按钮
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 使用偏移量来创建动画效果
        Text(
            text = "Hello, World!",
            modifier = Modifier.offset(x = animatedOffsetX.dp),
            color = Color.Black
        )
        Button(onClick = { }) {
            Text("Click me!")
        }
    }
}
