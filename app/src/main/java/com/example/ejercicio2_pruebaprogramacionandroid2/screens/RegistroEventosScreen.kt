package com.example.ejercicio2_pruebaprogramacionandroid2.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.database.FirebaseDatabase
import android.util.Log
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroEventosScreen(onEventoGuardado: () -> Unit, onCancelar: () -> Unit) {
    val database = FirebaseDatabase.getInstance().reference.child("eventos")
    val nombre = remember { mutableStateOf("") }
    val descripcion = remember { mutableStateOf("") }
    val direccion = remember { mutableStateOf("") }
    val precio = remember { mutableStateOf("") }
    val fecha = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            androidx.compose.material3.SmallTopAppBar(
                title = { Text("Registro de Eventos") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary, // Color de fondo
                    titleContentColor = MaterialTheme.colorScheme.onPrimary // Color del texto
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Campos de entrada
            OutlinedTextField(
                value = nombre.value,
                onValueChange = { nombre.value = it },
                label = { Text("Nombre del Evento") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            )
            OutlinedTextField(
                value = descripcion.value,
                onValueChange = { descripcion.value = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            )
            OutlinedTextField(
                value = direccion.value,
                onValueChange = { direccion.value = it },
                label = { Text("Dirección") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            )
            OutlinedTextField(
                value = precio.value,
                onValueChange = { precio.value = it },
                label = { Text("Precio") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            )
            OutlinedTextField(
                value = fecha.value,
                onValueChange = { fecha.value = it },
                label = { Text("Fecha (ej. 2023-12-01)") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botones
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        if (nombre.value.isNotBlank() && descripcion.value.isNotBlank()) {
                            val nuevoEvento = mapOf(
                                "nombre" to nombre.value,
                                "descripcion" to descripcion.value,
                                "direccion" to direccion.value,
                                "precio" to precio.value,
                                "fecha" to fecha.value
                            )
                            database.push().setValue(nuevoEvento)
                                .addOnSuccessListener {
                                    Log.d("RegistroEventosScreen", "Evento guardado correctamente")
                                    onEventoGuardado()
                                }
                                .addOnFailureListener {
                                    Log.e("RegistroEventosScreen", "Error al guardar evento: ${it.message}")
                                }
                        } else {
                            Log.e("RegistroEventosScreen", "Por favor, rellena todos los campos obligatorios")
                        }
                    }
                ) {
                    Text("Guardar")
                }
                Button(onClick = onCancelar) {
                    Text("Cancelar")
                }
            }
        }
    }
}


