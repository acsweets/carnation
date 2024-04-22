package com.example.lily

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import com.example.lily.ui.theme.CarnationTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarnationTheme {
                Greeting("你好，世界")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        delayAndNavigate()
    }


    //直线任务kotlin的协程
    private fun delayAndNavigate() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(3000L) // 延时 3 秒，单位为毫秒
            startActivityToNewScreen() // 调用跳转方法
        }
}

    private fun startActivityToNewScreen() {
        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
    }

//    private fun userNavigateToNewScreen() {
//        navController.navigate("details")
//    }

    }

//@Composable
//fun NavGraph() {
//    val navController = rememberNavController()
//    NavHost(
//        navController = navController,
//        startDestination = "home"
//    ) {
//        composable("home") {
//            HomeScreen(navController = navController)
//        }
//        composable("details") {
//            DetailsScreen()
//        }
//    }
//}
    @Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
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