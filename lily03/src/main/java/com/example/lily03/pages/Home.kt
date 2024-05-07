package com.example.lily03.pages
import android.annotation.SuppressLint
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import com.example.lily03.ui.theme.CarnationTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController: NavHostController){

    var selectedIndex by remember { mutableIntStateOf(0) }

    // 创建底部导航栏的项目
    val items = listOf(
        NavItem.Home,
        NavItem.Person,
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
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomePage(navController = navController) }
//        composable(
//            "details/{itemId}",
//            arguments = listOf(navArgument("itemId") {
//                type = NavType.IntType
//            })
//        ) { backStackEntry ->
//            DetailsScreen(
//                itemId = backStackEntry.arguments?.getInt("itemId"),
//                navController = navController
//            )
//
//        }
//        composable("other") { OtherScreen(navController = navController) }
    }
}
sealed class NavItem(var route: String, var icon: ImageVector, var title: String) {
    object Home : NavItem("home", Icons.Rounded.Home, "Home")
    object Person : NavItem("Person", Icons.Rounded.Person, "Person")
    object Settings : NavItem("settings", Icons.Rounded.Settings, "Settings")
}
