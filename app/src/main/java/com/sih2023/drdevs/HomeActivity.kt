package com.sih2023.drdevs

import android.icu.text.CaseMap.Title
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sih2023.drdevs.screens.HomeScreen
import com.sih2023.drdevs.screens.ProfileScreen
import com.sih2023.drdevs.screens.ReportScreen
import com.sih2023.drdevs.ui.theme.DRDevsTheme
import kotlinx.coroutines.launch

data class BottomNavigationItem(
    val route: String ,
    val title: String ,
    val selectedIcon : ImageVector ,
    val unselectedIcon :ImageVector ,
    val hasNews : Boolean ,
    val badgeCount : Int? = null
)
class HomeActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            val navController = rememberNavController()
            DRDevsTheme {
                val items = listOf(
                    BottomNavigationItem(
                        route = "home",
                        title = "Home",
                        selectedIcon = Icons.Filled.Home,
                        unselectedIcon = Icons.Outlined.Home,
                        hasNews = false,
                    ),
                    BottomNavigationItem(
                        route = "email",
                        title ="Email",
                        selectedIcon = Icons.Filled.Email,
                        unselectedIcon = Icons.Outlined.Email,
                        hasNews = false,
                        badgeCount = 38,
                    ),
                    BottomNavigationItem(
                        route = "settings",
                        title = "Settings",
                        selectedIcon = Icons.Filled.Settings,
                        unselectedIcon = Icons.Outlined.Settings,
                        hasNews = false,
                    ),
                )

                var selectedItemIndex by rememberSaveable {
                    mutableStateOf(0 )
                }
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

                    Scaffold(
                        bottomBar = {
                            NavigationBar {
                                items.forEachIndexed { index, item ->
                                    NavigationBarItem(
                                        selected = selectedItemIndex == index ,
                                        onClick = {
                                                  selectedItemIndex = index



                                        } ,
                                        label = {Text(text = item.title)},
                                        icon = {
                                            BadgedBox(badge = {
                                                if(item.badgeCount!=null) {
                                                    Badge {
                                                        Text(text = item.badgeCount.toString())
                                                    }
                                                }else if (item.hasNews){
                                                    Badge()
                                                }

                                            }) {
                                                Icon(
                                                    imageVector = if(index==selectedItemIndex){
                                                        item.selectedIcon
                                                    }else item.unselectedIcon,
                                                    contentDescription = item.title
                                                )
                                            }
                                        })
                                }

                            }
                        }
                    ) {
                        MainPageNavigation(navController = navController)
                    }
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
    DRDevsTheme {
        Greeting("Android")
    }
}


@Composable
fun MainPageNavigation(navController: NavController){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home", builder = {
        composable("home", content = { HomeScreen(navController = navController) })
        composable("email", content = { ProfileScreen(navController = navController) })
        composable("settings", content = { ReportScreen(navController = navController) })
    })
}
//                                          navController.navigate(item.route)