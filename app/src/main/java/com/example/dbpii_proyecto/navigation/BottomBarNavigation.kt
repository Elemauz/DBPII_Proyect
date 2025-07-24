package com.example.dbpii_proyecto.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        AppScreens.Inicio,
        AppScreens.Registrar,
        AppScreens.Listar,
        AppScreens.AboutUs
    )
    val currentBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = currentBackStackEntry?.destination?.route

    NavigationBar {
        items.forEach { screen ->
            screen.iconResId?.let { iconId ->
                NavigationBarItem(
                    selected = currentRoute == screen.route,
                    onClick = {
                        if (currentRoute != screen.route) {
                            navController.navigate(screen.route) {
                                popUpTo(AppScreens.Inicio.route) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    icon = {
                        Icon(
                            painter = androidx.compose.ui.res.painterResource(id = iconId),
                            contentDescription = screen.label
                        )
                    },
                    label = { Text(screen.label) },
                    alwaysShowLabel = true
                )
            }
        }
    }
}
