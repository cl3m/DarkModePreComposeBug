package com.darkmode.precompose.bug

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.darkmode.precompose.bug.ui.theme.DarkModeBugTheme

class MainActivity : ComponentActivity() {
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
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
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
            navController = navController,
            startDestination = Tab.Tab1.toString(),
            builder = {
                composable(Tab.Tab1.toString()) {
                    Text(Tab.Tab1.toString())
                }
                composable(Tab.Tab2.toString()) {
                    Text(Tab.Tab2.toString())
                }
                composable(Tab.Tab3.toString()) {
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
