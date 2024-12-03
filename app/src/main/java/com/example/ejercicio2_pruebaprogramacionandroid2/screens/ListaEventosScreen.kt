package com.example.ejercicio2_pruebaprogramacionandroid2.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.database.*
import android.util.Log
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import com.example.ejercicio2_pruebaprogramacionandroid2.EventoItem
import com.example.ejercicio2_pruebaprogramacionandroid2.data.Evento

@Composable
fun ListaEventosScreen(navigateToRegistro: () -> Unit) {
    val database = FirebaseDatabase.getInstance().reference.child("eventos")
    val eventos = remember { mutableStateListOf<Evento>() }

    // Leer eventos desde Firebase
    LaunchedEffect(Unit) {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                eventos.clear()
                for (child in snapshot.children) {
                    val evento = child.getValue(Evento::class.java)
                    if (evento != null) {
                        eventos.add(evento)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("ListaEventosScreen", "Error al cargar eventos: ${error.message}")
            }
        })
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = navigateToRegistro) {
                Text("+") // Botón para agregar eventos
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it).padding(16.dp)
        ) {
            items(eventos) { evento ->
                EventoItem(evento)
            }
        }
    }
}
