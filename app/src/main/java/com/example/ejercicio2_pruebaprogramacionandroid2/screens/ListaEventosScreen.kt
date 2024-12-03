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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.res.stringResource
import com.example.ejercicio2_pruebaprogramacionandroid2.EventoItem
import com.example.ejercicio2_pruebaprogramacionandroid2.R
import com.example.ejercicio2_pruebaprogramacionandroid2.data.Evento

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaEventosScreen(navigateToRegistro: () -> Unit) {
    val database = FirebaseDatabase.getInstance().reference.child("eventos")
    val eventos = remember { mutableStateListOf<Evento>() }

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
        topBar = {
            androidx.compose.material3.SmallTopAppBar(
                title = { Text(stringResource(id = R.string.eventos)) },
                actions = {
                    IconButton(onClick = navigateToRegistro) {
                        androidx.compose.material3.Icon(
                            imageVector = androidx.compose.material.icons.Icons.Default.Add,
                            contentDescription = stringResource(id = R.string.registro_eventos),
                            tint = androidx.compose.ui.graphics.Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = androidx.compose.ui.graphics.Color(0xFF1976D2),
                    titleContentColor = androidx.compose.ui.graphics.Color.White
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            items(eventos) { evento ->
                EventoItem(evento)
            }
        }
    }
}

