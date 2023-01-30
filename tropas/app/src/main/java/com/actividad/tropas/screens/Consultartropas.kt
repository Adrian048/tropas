package com.actividad.tropas.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun Consultartropas(navController: NavHostController) {

    var nombre_coleccion = "tropas"
    val db = FirebaseFirestore.getInstance()
    //var datos by remember { mutableStateOf("") }

    Column(

        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp)
            .padding(start = 10.dp)
            .padding(end = 10.dp)
    ) {

        Text(
            text = "Búsqueda de tropa por nombre",
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.size(20.dp))

        //DECLARAMOS LA VARIABLE QUE VA A RECOGER LOS DATOS DE LA CONSULTA CON EL ESTADO REMEMBER
        var datos by remember { mutableStateOf("") }
        var nombre_tropa by remember { mutableStateOf("") }
        var espacio_tropa by remember { mutableStateOf("") }
        var nivel_tropa by remember { mutableStateOf("") }
        var danio_tropa by remember { mutableStateOf("") }
        var vida_tropa by remember { mutableStateOf("") }


        var nombre_busqueda by remember { mutableStateOf("") }
        val field_busqueda ="nombre"
        OutlinedTextField(
            value = nombre_busqueda,
            onValueChange = { nombre_busqueda = it },
            label = { Text("Introduce el nombre a consultar") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )

        Spacer(modifier = Modifier.size(5.dp))

        Button(
            onClick = {

                // VACIAMOS VARIABLE AL DAR AL BOTON
                datos = ""
                nombre_tropa = ""
                espacio_tropa = ""
                nivel_tropa = ""
                danio_tropa = ""
                vida_tropa = ""

                // HACEMOS LA CONSULTA A LA COLECCION CON GET
                db.collection(nombre_coleccion)
                    .whereEqualTo(field_busqueda,nombre_busqueda)
                    .get()

                    //SI SE CONECTA CORRECTAMENTE
                    // RECORRO TODOS LOS DATOS ENCONTRADOS EN LA COLECCIÓN Y LOS ALMACENO EN DATOS
                    .addOnSuccessListener { resultado ->
                        for (encontrado in resultado) {
                            //Para crear un HashMap con todos los datos
                            datos += "${encontrado.id}: ${encontrado.data}\n"

                            //Para crear un HashMap con todos los datos
                            nombre_tropa += encontrado["nombre"].toString()
                            espacio_tropa += encontrado["espacio"].toString()
                            nivel_tropa += encontrado["nivel"].toString()
                            danio_tropa += encontrado["danio"].toString()
                            vida_tropa += encontrado["vida"].toString()

                            //Log.i("DATOS:", datos)
                        }

                        if (datos.isEmpty()) {
                            datos = "No existen datos"
                        }
                    }
                    //SI NO CONECTA CORRECTAMENTE
                    .addOnFailureListener { resultado ->
                        datos = "La conexión a FireStore no se ha podido completar"
                    }
            },

            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Blue,
                contentColor = Color.White
            ),
            border = BorderStroke(1.dp, Color.Black)
        )
        {

            Text(text = "Cargar Datos")
        }

        Spacer(modifier = Modifier.size(10.dp))

        // PINTAMOS EL RESULTADO DE LA CONSULTA A LA BASE DE DATOS
        //Text (text = datos)
        Text (text = "Nombre: " + nombre_tropa)
        Text (text = "Espacio: " + espacio_tropa)
        Text (text = "Nivel: " + nivel_tropa)
        Text (text = "Daño: " + danio_tropa)
        Text (text = "Vida: " + vida_tropa)
    }
}