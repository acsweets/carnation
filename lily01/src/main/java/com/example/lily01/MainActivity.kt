package com.example.lily01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lily01.ui.theme.CarnationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarnationTheme {



                Column(
                    modifier = Modifier.fillMaxSize()
                ) {

                    ItemExample("屏幕显示") { Item1() }
                    ItemExample("简单布局") { Item2() }

                    ItemExample("简单控件") { Item3() }

                    ItemExample("图形基础") { Item4() }
                    ItemExample("计算器") {Item5() }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CarnationTheme {
        Greeting("Android")
    }
}

@Composable
fun ItemExample(name: String, child: @Composable () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier= Modifier.height(10.dp))
        Text(text = name, textAlign = TextAlign.Center)
        Box(modifier= Modifier.height(10.dp))
        child()
    }

}

@Composable
fun Item1() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            GotoButton({}, "像素")
            GotoButton({}, "颜色")
            GotoButton({}, "分辨率")

        }
    }

}

@Composable
fun Item2() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            GotoButton({}, "空白间隔")
            GotoButton({}, "对齐方式")
            GotoButton({}, "滚动视图")

        }
    }

}

@Composable
fun Item3() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            GotoButton({}, "跑马灯")
            GotoButton({}, "聊天室")
            GotoButton({}, "按钮点击")

        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            GotoButton({}, "图像拉伸")
            GotoButton({}, "屏幕截图")
            GotoButton({}, "变换图标")

        }
    }

}

@Composable
fun Item4() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            GotoButton({}, "状态列表图形")
            GotoButton({}, "形状定义图形")
            GotoButton({}, "九宫格图片")

        }
    }

}

@Composable
fun Item5() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        GotoButton({}, "简单计算器")
    }

}

@Composable
fun GotoButton(onClick: () -> Unit, name: String) {
    Button(onClick = onClick) {
        Text(text = name )
    }

}

