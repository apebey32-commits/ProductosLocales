package com.example.productoslocales

data class Producto(
    val nombre: String,
    val precio: String,
    val localidad: String,
    val productor: String,
    val descripcion: String,
    val categoria: String
)

val productos = listOf(

    Producto(
        nombre = "🍎 Manzanas",
        precio = "$1.500",
        localidad = "Rancagua, O'Higgins",
        productor = "Agricultores del Valle",
        descripcion = "Manzanas frescas producidas localmente.",
        categoria = "Frutas"
    ),

    Producto(
        nombre = "🍯 Miel artesanal",
        precio = "$4.000",
        localidad = "San Fernando, O'Higgins",
        productor = "Apicultores de O'Higgins",
        descripcion = "Miel artesanal elaborada a partir de flores locales.",
        categoria = "Artesanales"
    ),

    Producto(
        nombre = "🥖 Pan casero",
        precio = "$2.000",
        localidad = "Rengo, O'Higgins",
        productor = "Panadería El Valle",
        descripcion = "Pan casero elaborado de manera artesanal.",
        categoria = "Panadería"
    ),

    Producto(
        nombre = "🧀 Queso artesanal",
        precio = "$5.000",
        localidad = "Santa Cruz, O'Higgins",
        productor = "Lácteos del Valle",
        descripcion = "Queso artesanal elaborado con leche de productores locales.",
        categoria = "Lácteos"
    ),

    Producto(
        nombre = "🍓 Frutillas",
        precio = "$2.500",
        localidad = "San Fernando, O'Higgins",
        productor = "Frutillas del Valle",
        descripcion = "Frutillas frescas de producción local.",
        categoria = "Frutas"
    ),

    Producto(
        nombre = "🥚 Huevos de campo",
        precio = "$3.500",
        localidad = "Pichilemu, O'Higgins",
        productor = "Granja Los Robles",
        descripcion = "Huevos frescos provenientes de gallinas de campo.",
        categoria = "Huevos"
    )
)