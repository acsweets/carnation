package com.example.lily04

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
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lily04.ui.theme.CarnationTheme
import com.example.lily04.widget.AnimatedText

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarnationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavHost()
                }
            }
        }
    }

    override fun onStart() { // 开始活动
        super.onStart()
        Log.d("onStart", "onStart") // 刷新生命周期的日志信息
    }

    override fun onStop() { // 停止活动
        super.onStop()
        Log.d("onStop", "onStop") // 刷新生命周期的日志信息
    }

    override fun onResume() { // 恢复活动
        super.onResume()
        Log.d("onResume", "onResume") // 刷新生命周期的日志信息
    }

    override fun onPause() { // 暂停活动
        super.onPause()
        Log.d("onPause", "onResume") // 刷新生命周期的日志信息
    }

    override fun onRestart() { // 重启活动
        super.onRestart()
        Log.d("onRestart", "onResume") // 刷新生命周期的日志信息
    }

    override fun onDestroy() { // 销毁活动
        super.onDestroy()
        Log.d("onDestroy", "onResume") // 刷新生命周期的日志信息
    }

}
// 定义进入动画资源 ID
val enterAnim = R.anim.slide_in_right

// 定义退出动画资源 ID
val exitAnim = R.anim.slide_out_left
val navOptions =
    NavOptions.Builder().setEnterAnim(enterAnim).setExitAnim(exitAnim).build()
@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { SimplePage(navController = navController) }
        composable(
            "details/{itemId}",
            arguments = listOf(navArgument("itemId") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            DetailsScreen(
                itemId = backStackEntry.arguments?.getInt("itemId"),
                navController = navController
            )

        }
        composable("other") { OtherScreen(navController = navController) }
    }
}


@Composable
fun SimplePage(navController: NavHostController) {

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        selectedImageUri = uri
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { launcher.launch("image/*") }) {
            Text("Open Gallery")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (selectedImageUri != null) {
            Image(
                painter = rememberAsyncImagePainter(selectedImageUri),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
        } else {
            Text("No image selected")
        }


        Button(onClick = {
            navController.navigate("details/123", navOptions=navOptions)
        }) {
            Text("Go to Details")
        }
    }
}


@Composable
fun DetailsScreen(itemId: Int?, navController: NavHostController) {


    Column {
        Text("Details Screen")
        Text("Item ID: $itemId")
        Button(onClick = { navController.navigate("other") }) {
            Text("Go to Other Screen")
        }
    }
}

@Composable
fun OtherScreen(navController: NavHostController) {
    Column {
        Text("Other Screen")
        // 添加其他UI元素
        AnimatedText()
    }
}