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
// FUNCION PARA BORRAR UN DOCUMENTO DE LA COLECCION
fun Borrartropas(navController: NavHostController) {

    val db = FirebaseFirestore.getInstance()

    var nombre_coleccion = "tropas"
    var nombre_tropa by remember { mutableStateOf("") }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 12.dp,
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
                text = "Eliminar tropa",
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.size(10.dp))

            OutlinedTextField(
                value = nombre_tropa,
                onValueChange = { nombre_tropa = it },
                label = { Text("Introduce el nombre borrar") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.size(5.dp))

            var mensaje_borrado by remember { mutableStateOf("") }

            Button(

                onClick = {
                    if (nombre_tropa.isNotBlank()) {
                        db.collection(nombre_coleccion)
                            .document(nombre_tropa)
                            .delete()
                            .addOnSuccessListener {
                                mensaje_borrado = "Datos borrados correctamente"
                                nombre_tropa = ""
                            }
                            .addOnFailureListener {
                                mensaje_borrado = "No se ha podido borrar"
                                nombre_tropa = ""
                            }
                    }
                },

                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Blue,
                    contentColor = Color.White
                ),
                border = BorderStroke(1.dp, Color.Black)
            )
            {

                Text(text = "Borrar")


            }
            Spacer(modifier = Modifier.size(5.dp))
            Text(text = mensaje_borrado)
        }

    }

}