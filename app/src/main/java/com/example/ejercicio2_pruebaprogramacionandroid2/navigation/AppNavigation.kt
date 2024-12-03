package com.example.ejercicio2_pruebaprogramacionandroid2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ejercicio2_pruebaprogramacionandroid2.screens.ListaEventosScreen
import com.example.ejercicio2_pruebaprogramacionandroid2.screens.RegistroEventosScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "listaEventos") {
        // Pantalla de la lista de eventos
        composable("listaEventos") {
            ListaEventosScreen(
                navigateToRegistro = { navController.navigate("registroEventos") }
            )
        }

        // Pantalla para registrar un nuevo evento
        composable("registroEventos") {
            RegistroEventosScreen(
                onEventoGuardado = { navController.navigate("listaEventos") },
                onCancelar = { navController.navigate("listaEventos") }
            )
        }
    }
}
