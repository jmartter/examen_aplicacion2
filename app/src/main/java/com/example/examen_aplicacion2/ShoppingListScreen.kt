package com.example.examen_aplicacion2

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun ShoppingListScreen(db: FirebaseFirestore) {
    var nombre by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    val productos = remember { mutableStateListOf<Producto>() }
    var errorMessage by remember { mutableStateOf("") }
    var firebaseError by remember { mutableStateOf("") }
    var totalProductos by remember { mutableStateOf(0) }
    var totalPrecio by remember { mutableStateOf(0.0) }

    val productosRef = db.collection("productos")

    // Fetch products from Firebase
    LaunchedEffect(Unit) {
        productosRef.get()
            .addOnSuccessListener { result ->
                val productosList = result.toObjects(Producto::class.java)
                productos.clear()
                productos.addAll(productosList)
                totalProductos = productosList.size
                totalPrecio = productosList.sumOf { it.precio * it.cantidad }
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Error al obtener productos: ${exception.message}")
                firebaseError = "Error fetching products: ${exception.message}"
            }
    }

    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Column(modifier = Modifier.fillMaxWidth().align(Alignment.TopCenter)) {
            Text(text = "Lista de la compra: $totalProductos productos, Total: $totalPrecio €")
            productos.forEach { producto ->
                val totalProducto = producto.cantidad * producto.precio
                Text(text = "${producto.nombre} - ${producto.cantidad} - ${producto.precio} = $totalProducto")
            }
            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = androidx.compose.ui.graphics.Color.Red)
            }
            if (firebaseError.isNotEmpty()) {
                Text(text = firebaseError, color = androidx.compose.ui.graphics.Color.Red)
            }
        }
        Column(modifier = Modifier.align(Alignment.BottomCenter)) {
            Row(modifier = Modifier.fillMaxWidth().padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Producto") },
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
                        productosRef.add(producto)
                            .addOnSuccessListener {
                                Log.d("Firestore", "Producto agregado exitosamente: $producto")
                                // Clear fields on success
                                nombre = ""
                                cantidad = ""
                                precio = ""
                                errorMessage = ""
                                firebaseError = ""
                                // Update totals
                                totalProductos += 1
                                totalPrecio += producto.precio * producto.cantidad
                            }
                            .addOnFailureListener { exception ->
                                Log.e("Firestore", "Error al guardar en Firestore: ${exception.message}")
                                firebaseError = "Error saving to Firestore: ${exception.message}"
                            }
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