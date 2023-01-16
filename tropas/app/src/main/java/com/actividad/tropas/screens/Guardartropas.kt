package com.actividad.tropas.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore

@Composable

fun Guardartropas(navController: NavHostController) {

    val db = FirebaseFirestore.getInstance()

    var nombre_coleccion = "tropas"
    var nombre_tropa by remember { mutableStateOf("") }
    var espacio_tropa by remember { mutableStateOf("") }
    var nivel_tropa by remember { mutableStateOf("") }
    var danio_tropa by remember { mutableStateOf("") }
    var vida_tropa by remember { mutableStateOf("") }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 112.dp,
        shape = MaterialTheme.shapes.small,
        backgroundColor = Color.White,
        contentColor = Color.DarkGray,
        border = BorderStroke(1.dp, Color.Blue)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp)
                .padding(start= 10.dp)
                .padding(end= 10.dp)

        ) {

            Text(
                text = "Guardar tropa",
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.size(20.dp))

            OutlinedTextField(
                value = nombre_tropa,
                onValueChange = { nombre_tropa = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.size(5.dp))

            OutlinedTextField(
                value = espacio_tropa,
                onValueChange = { espacio_tropa = it },
                label = { Text("Espacio") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.size(5.dp))

            OutlinedTextField(
                value = nivel_tropa,

                onValueChange = { nivel_tropa = it },
                label = { Text("Nivel") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.size(5.dp))



            OutlinedTextField(
                value = danio_tropa,
                onValueChange = { danio_tropa = it },
                label = { Text("Daño/segundo") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.size(10.dp))

            OutlinedTextField(
                value = vida_tropa,
                onValueChange = { vida_tropa = it },
                label = { Text("Vida") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.size(5.dp))
            val dato = hashMapOf(
                "nombre" to nombre_tropa.toString(),
                "espacio" to espacio_tropa.toString(),
                "nivel" to nivel_tropa.toString(),
                "daño/segundo" to danio_tropa.toString(),
                "vida" to vida_tropa.toString()
            )

            var mensaje_confirmacion by remember { mutableStateOf("") }

            Button(

                onClick = {
                    db.collection(nombre_coleccion)
                        .document(nombre_tropa)
                        .set(dato)
                        .addOnSuccessListener {
                            mensaje_confirmacion ="Datos guardados correctamente"
                            nombre_tropa=""
                            espacio_tropa=""
                            nivel_tropa=""
                            vida_tropa=""
                            danio_tropa=""
                        }
                        .addOnFailureListener {
                            mensaje_confirmacion ="No se ha podido guardar"
                            nombre_tropa=""
                            espacio_tropa=""
                            nivel_tropa=""
                            vida_tropa=""
                            danio_tropa=""
                            println(nombre_coleccion)
                        }
                },

                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Blue,
                    contentColor = Color.White
                ),
                border = BorderStroke(1.dp, Color.Black)
            )
            {

                Text(text = "Guardar")


            }
            Spacer(modifier = Modifier.size(5.dp))
            Text(text = mensaje_confirmacion)
        }

    }
}