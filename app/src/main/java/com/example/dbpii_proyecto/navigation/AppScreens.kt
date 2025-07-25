package com.example.dbpii_proyecto.navigation

import com.example.dbpii_proyecto.R

sealed class AppScreens(
    val route: String,
    val label: String,
    val iconResId: Int? = null
) {
    object Inicio : AppScreens("Inicio", "Inicio", R.drawable.home)
    object Login : AppScreens("Login", "Login")
    object Registrar : AppScreens("Registrar", "Registrar", R.drawable.registrar)
    object Listar : AppScreens("Listar", "Listar", R.drawable.listar)
    object AboutUs : AppScreens("AboutUs", "Sobre Mi", R.drawable.cuenta_picture)
    object SignUp: AppScreens("SignUp", "SignUp")
}
