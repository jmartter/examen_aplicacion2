# Examen Aplicacion 2

## Enlace al Repositorio
[Repositorio en GitHub](https://github.com/jmartter/examen_aplicacion2.git)

## Descripción

Examen Aplicación 2 es una aplicación de lista de compras desarrollada en Kotlin utilizando Jetpack Compose para la interfaz de usuario y Firebase Firestore para el almacenamiento de datos. La aplicación permite a los usuarios agregar, visualizar y eliminar productos de una lista de compras. Los productos se almacenan en una base de datos Firestore y se sincronizan en tiempo real.

## Estructura del Proyecto

El proyecto está compuesto por los siguientes archivos principales:

- `MainActivity.kt`: La actividad principal que inicializa Firestore y establece el contenido de la aplicación.
- `ShoppingListScreen.kt`: Un archivo que contiene la función composable `ShoppingListScreen`, que maneja la interfaz de usuario y la lógica de la lista de compras.
- `Producto.kt`: Un archivo que define la clase de datos `Producto`, que representa un producto en la lista de compras.
- `build.gradle.kts`: El archivo de configuración de Gradle que incluye las dependencias necesarias para el proyecto.

## Funcionalidades

### Agregar Producto

Los usuarios pueden agregar un nuevo producto a la lista de compras ingresando el nombre, la cantidad y el precio del producto. Al hacer clic en el botón "Agregar Producto", el producto se agrega a la lista y se guarda en Firestore.

### Visualizar Productos

La lista de compras muestra todos los productos almacenados en Firestore. Cada producto se muestra con su nombre, cantidad y precio total.

### Eliminar Producto

Los usuarios pueden eliminar un producto de la lista de compras haciendo clic en el producto y seleccionando "Eliminar" en el menú desplegable. El producto se elimina de la lista y de Firestore.

