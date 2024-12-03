package com.example.ejercicio2_pruebaprogramacionandroid2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import com.example.ejercicio2_pruebaprogramacionandroid2.data.Evento

@Composable
fun EventoItem(evento: Evento) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Reemplazo del ícono: Un cuadro de color como marcador
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .padding(8.dp)
                    .background(MaterialTheme.colorScheme.primary)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Detalles del evento
            Column {
                Text(
                    text = evento.nombre,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = evento.descripcion,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}