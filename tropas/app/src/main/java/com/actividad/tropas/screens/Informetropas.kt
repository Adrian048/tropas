package com.actividad.tropas.screens


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
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
fun Informetropas(navController: NavHostController) {


    val db = FirebaseFirestore.getInstance()


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp)
            .padding(start = 5.dp)
            .padding(end = 5.dp)
    ) {

        Text(
            text = "Seleccionar todos los datos en Cloud FireStore",
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.size(20.dp))

        //DECLARAMOS LA VARIABLE QUE VA A RECOGER LOS DATOS DE LA CONSULTA CON EL ESTADO REMEMBER
        var datos by remember { mutableStateOf("") }

        Button(
            onClick = {

                // VACIAMOS VARIABLE AL DAR AL BOTON
                datos = ""

                // HACEMOS LA CONSULTA A LA COLECCION CON GET
                db.collection("tropas")
                    .get()

                    //SI SE CONECTA CORRECTAMENTE
                    // RECORRO TODOS LOS DATOS ENCONTRADOS EN LA COLECCIÓN Y LOS ALMACENO EN DATOS
                    .addOnSuccessListener { resultado ->
                        for (encontrado in resultado) {
                            datos += "${encontrado.id}: ${encontrado.data}\n\n"
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



        Text (text = datos)

    }
}
