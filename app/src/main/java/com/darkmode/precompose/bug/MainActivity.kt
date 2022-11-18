package com.darkmode.precompose.bug

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
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
    var currentRoute by remember { mutableStateOf(Tab.Tab1.toString()) }
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
                            currentRoute = screen.toString()
                        }
                    )
                }
            }
        }
    ) {
        Text(currentRoute)
    }
}

enum class Tab {
    Tab1,
    Tab2,
    Tab3
}
