package com.example.productoslocales

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.productoslocales.ui.theme.ProductosLocalesTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ProductosLocalesTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    PantallaPrincipal(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PantallaPrincipal(
    modifier: Modifier = Modifier
) {

    var textoBusqueda by remember {
        mutableStateOf("")
    }

    var categoriaSeleccionada by remember {
        mutableStateOf("Todos")
    }

    var productoSeleccionado by remember {
        mutableStateOf<Producto?>(null)
    }

    var mostrarFormulario by remember {
        mutableStateOf(false)
    }

    if (mostrarFormulario) {

        FormularioConsulta(
            onVolver = {
                mostrarFormulario = false
            },
            modifier = modifier
        )

    } else if (productoSeleccionado == null) {

        val categorias = listOf(
            "Todos",
            "Frutas",
            "Artesanales",
            "Panadería",
            "Lácteos",
            "Huevos"
        )

        val productosFiltrados = productos.filter { producto ->

            val coincideBusqueda =
                producto.nombre.contains(
                    textoBusqueda,
                    ignoreCase = true
                )

            val coincideCategoria =
                categoriaSeleccionada == "Todos" ||
                        producto.categoria == categoriaSeleccionada

            coincideBusqueda && coincideCategoria
        }

        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                text = "Productos Locales"
            )

            Text(
                text = "Encuentra productos de la Región de O'Higgins"
            )

            OutlinedTextField(
                value = textoBusqueda,
                onValueChange = {
                    textoBusqueda = it
                },
                label = {
                    Text("Buscar producto")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Categorías"
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                categorias.forEach { categoria ->

                    Button(
                        onClick = {
                            categoriaSeleccionada = categoria
                        }
                    ) {
                        Text(categoria)
                    }
                }
            }

            Button(
                onClick = {
                    mostrarFormulario = true
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("📝 Enviar consulta")
            }

            productosFiltrados.forEach { producto ->

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {

                        Text(
                            text = producto.nombre
                        )

                        Text(
                            text = producto.precio
                        )

                        Text(
                            text = "📍 ${producto.localidad}"
                        )

                        Text(
                            text = "Categoría: ${producto.categoria}"
                        )

                        Button(
                            onClick = {
                                productoSeleccionado = producto
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Ver producto")
                        }
                    }
                }
            }
        }

    } else {

        DetalleProducto(
            producto = productoSeleccionado!!,
            onVolver = {
                productoSeleccionado = null
            },
            modifier = modifier
        )
    }
}

@Composable
fun DetalleProducto(
    producto: Producto,
    onVolver: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Detalle del producto"
        )

        Text(
            text = producto.nombre
        )

        Text(
            text = "Precio: ${producto.precio}"
        )

        Text(
            text = "📍 Localidad: ${producto.localidad}"
        )

        Text(
            text = "🏷️ Categoría: ${producto.categoria}"
        )

        Text(
            text = "👨‍🌾 Productor: ${producto.productor}"
        )

        Text(
            text = "📝 ${producto.descripcion}"
        )

        Button(
            onClick = onVolver
        ) {
            Text("Volver")
        }
    }
}

@Composable
fun FormularioConsulta(
    onVolver: () -> Unit,
    modifier: Modifier = Modifier
) {

    var nombre by remember {
        mutableStateOf("")
    }

    var correo by remember {
        mutableStateOf("")
    }

    var consulta by remember {
        mutableStateOf("")
    }

    var consultaEnviada by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Formulario de consulta"
        )

        Text(
            text = "Completa los datos para enviar tu consulta."
        )

        OutlinedTextField(
            value = nombre,
            onValueChange = {
                nombre = it
            },
            label = {
                Text("Nombre")
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = correo,
            onValueChange = {
                correo = it
            },
            label = {
                Text("Correo electrónico")
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = consulta,
            onValueChange = {
                consulta = it
            },
            label = {
                Text("Consulta")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )

        Button(
            onClick = {
                consultaEnviada = true
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enviar consulta")
        }

        if (consultaEnviada) {

            Text(
                text = "✅ Consulta enviada correctamente."
            )
        }

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Button(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver al catálogo")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaPrincipalPreview() {
    ProductosLocalesTheme {
        PantallaPrincipal()
    }
}