package com.example.examen_aplicacion2

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment

@Composable
fun ShoppingListScreen() {
    var nombre by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    val productos = remember { mutableStateListOf<Producto>() }
    var errorMessage by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Column(modifier = Modifier.fillMaxWidth().align(Alignment.TopCenter)) {
            productos.forEach { producto ->
                Text(text = "${producto.nombre} - ${producto.cantidad} - ${producto.precio}")
            }
            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = androidx.compose.ui.graphics.Color.Red)
            }
        }
        Column(modifier = Modifier.align(Alignment.BottomCenter)) {
            Row(modifier = Modifier.fillMaxWidth().padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre del producto") },
                    modifier = Modifier.weight(1f).padding(8.dp)
                )
                OutlinedTextField(
                    value = cantidad,
                    onValueChange = { cantidad = it },
                    label = { Text("Cantidad") },
                    modifier = Modifier.weight(1f).padding(8.dp)
                )
                OutlinedTextField(
                    value = precio,
                    onValueChange = { precio = it },
                    label = { Text("Precio") },
                    modifier = Modifier.weight(1f).padding(8.dp)
                )
            }
            Button(
                onClick = {
                    if (nombre.isNotEmpty()) {
                        val producto = Producto(
                            nombre = nombre,
                            cantidad = cantidad.toIntOrNull() ?: 0,
                            precio = precio.toDoubleOrNull() ?: 0.0
                        )
                        productos.add(producto)
                        nombre = ""
                        cantidad = ""
                        precio = ""
                        errorMessage = ""
                    } else {
                        errorMessage = "El nombre del producto es obligatorio."
                    }
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Agregar Producto")
            }
        }
    }
}