package com.example.lily

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.lily.ui.theme.CarnationTheme

/////底部导航栏的实现
//
class MainActivity3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarnationTheme {
                // A surface container using the 'background' color from the theme
                BottomNavigationExample()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationExample() {
    // 创建底部导航栏的状态
    var selectedIndex by remember { mutableIntStateOf(0) }

    // 创建底部导航栏的项目
    val items = listOf(
        NavItem.Home,
        NavItem.Profile,
        NavItem.Settings
    )

    Scaffold(
        bottomBar = {
            NavigationBar() {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(imageVector = item.icon, contentDescription = null) },
                        label = { Text(text = item.title) },
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index }
                    )
                }
            }


        }
    ) {
        // 根据选中的索引显示不同的页面内容
        when (selectedIndex) {
            0 -> TabContent(text = "Tab 1 Content")
            1 -> TabContent(text = "Tab 2 Content")
            2 -> TabContent(text = "Tab 3 Content")
        }
    }
}

@Composable
fun TabContent(text: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = text)
    }
}


// 底部导航
sealed class NavItem(var route: String, var icon: ImageVector, var title: String) {
    object Home : NavItem("home", Icons.Rounded.Home, "Home")
    object Profile : NavItem("profile", Icons.Rounded.Person, "Profile")
    object Settings : NavItem("settings", Icons.Rounded.Settings, "Settings")
}
