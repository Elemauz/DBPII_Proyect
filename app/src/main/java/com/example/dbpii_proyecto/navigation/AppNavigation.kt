package com.example.dbpii_proyecto.navigation
import com.example.dbpii_proyecto.R;

import AboutUs
import Inicio
import Listar
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.example.dbpii_proyecto.FinanzasGlobal
import com.example.dbpii_proyecto.Pantallas.*
import com.example.dbpii_proyecto.ResumenFinanciero
import com.example.dbpii_proyecto.TransaccionesGlobales
import com.example.test7.ui.screens.Registrar

@Composable
fun AppNavigation(intentDestino: String?) {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    LaunchedEffect(intentDestino) {
        if (!intentDestino.isNullOrEmpty() &&
            intentDestino !in listOf(AppScreens.Login.route, AppScreens.SignUp.route)
        ) {
            navController.navigate(intentDestino)
        }
    }

    Scaffold(
        bottomBar = {
            if (currentRoute in listOf(
                    AppScreens.Inicio.route,
                    AppScreens.Registrar.route,
                    AppScreens.Listar.route,
                    AppScreens.AboutUs.route
                )
            ) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppScreens.Inicio.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(AppScreens.Login.route) {
                Login(
                    navController
                )
            }

            composable(AppScreens.SignUp.route) {
                SignUp(
                    navController

                )
            }

            composable(AppScreens.Inicio.route) {
                Inicio( FinanzasGlobal, navController)
            }

            composable(AppScreens.Registrar.route) {
                Registrar(FinanzasGlobal, navController)
            }

            composable(AppScreens.Listar.route) {
                Listar(
                    navController = navController,
                    transacciones = TransaccionesGlobales.lista,
                    onItemClick = { transaccion ->
                        println("Transacción seleccionada: ${transaccion.titulo}")
                    }
                )
            }



            composable(AppScreens.AboutUs.route) {
                AboutUs(navController)
            }
        }
    }
}
