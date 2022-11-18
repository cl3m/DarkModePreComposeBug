package com.darkmode.precompose.bug

import android.os.Bundle
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import com.darkmode.precompose.bug.ui.theme.DarkModeBugTheme
import moe.tlaster.precompose.lifecycle.PreComposeActivity
import moe.tlaster.precompose.lifecycle.setContent
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator

class MainActivity : PreComposeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DarkModeBugTheme {
                BottomNavigationBug()
            }
        }
    }
}

@Composable
internal fun BottomNavigationBug() {
    val navController = rememberNavigator()
    val navBackStackEntry by navController.currentEntry.collectAsState(null)
    val currentRoute = navBackStackEntry?.route?.route
    Scaffold(
        bottomBar = {
            BottomNavigation {
                Tab.values().forEach { screen ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = null
                            )
                        },
                        label = { Text(screen.toString()) },
                        selected = currentRoute == screen.toString(),
                        onClick = {
                            navController.navigate(screen.toString())
                        }
                    )
                }
            }
        }
    ) {
        NavHost(
            navigator = navController,
            initialRoute = Tab.Tab1.toString(),
            builder = {
                scene(Tab.Tab1.toString()) {
                    Text(Tab.Tab1.toString())
                }
                scene(Tab.Tab2.toString()) {
                    Text(Tab.Tab2.toString())
                }
                scene(Tab.Tab3.toString()) {
                    Text(Tab.Tab3.toString())
                }
            })
    }
}

enum class Tab {
    Tab1,
    Tab2,
    Tab3
}
