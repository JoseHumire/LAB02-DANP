package com.example.danp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database

import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

//abstract class ReadAndWriteSnippets {
//
//    private val TAG = "ReadAndWriteSnippets"
//
//    // [START declare_database_ref]
//    private lateinit var database: DatabaseReference
//    // [END declare_database_ref]
//
//    fun initializeDbRef() {
//        // [START initialize_database_ref]
//        database = Firebase.database.reference
//        // [END initialize_database_ref]
//    }
//}

lateinit var m_database: DatabaseReference

class BusesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        m_database = Firebase.database.reference
        setContent {
            BusesList()
        }
    }
}

@Composable
fun BusesList(){
    // Se obtiene el código del distrito enviado en el Intent
    val context = LocalContext.current
    val activity = context.findActivity()
    val intent = activity?.intent
    val districtCode = intent?.extras?.getInt("district_code")
    // Se filtra los buses que pertenecen al distrito
    val buses = busesList.filter { it.districtCode == districtCode }
    val distrito = districtsList.filter { it.code == districtCode }[0]

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ){
        item {
            TitleList(title = "Buses en " + distrito.name)
        }
        items(buses){
            BusItemList(bus = it)
        }
    }
}

@Composable
fun BusItemList(bus: BusLine){
    val context = LocalContext.current
    val openDialog = remember { mutableStateOf(false) }
    Card(
        modifier = Modifier.padding(horizontal = 2.dp, vertical = 2.dp).fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Color(myColorBackground.toColorInt()),
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ){
        Row(
            modifier = Modifier
                .padding(10.dp)
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.padding(7.dp)) {

                Text(
                    text = bus.name,
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Color(myColorPrimary.toColorInt()),
                        fontStyle = FontStyle.Italic
                    )
                )
                //
                Text(text = "Ruta: " + bus.route, style = TextStyle(fontSize = 15.sp))
                //
                Spacer(modifier = Modifier.height(1.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(color = Color(myColorSecond.toColorInt()))
                )
                Row(
                    modifier = Modifier.height(IntrinsicSize.Min).fillMaxWidth().fillMaxHeight(),
                    horizontalArrangement = Arrangement.Center
                ){
                    Button(onClick = {
                        m_database.child("user").child("bus").setValue(bus.name)
                        val intent = Intent(context, MapsActivity::class.java)
                        intent.putExtra("bus_name", bus.name.toString())
                        context.startActivity(intent)
                    },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(myColorBackground.toColorInt())),
                        modifier = Modifier.padding(0.dp).width(130.dp)
                    ){
                        Text(text = "Ver ruta", style = TextStyle(color = Color(myColorSecond.toColorInt())))
                    }
                    Divider(
                        color = Color(myColorSecond.toColorInt()),
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(2.dp)
                    )
                    Button(onClick = {
                        //El boton abre el dialogo y vuelve verdadero el estado del dialogo
                        openDialog.value = true
                    },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(myColorBackground.toColorInt())),
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Text(text = "Ver información", style = TextStyle(color = Color(myColorSecond.toColorInt())))
                    }
                }

            }

        }
    }
//  °Mostrar dialogo con información de empresa de transporte
    if (openDialog.value) {
        //La alerta se inicializa en falso
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            //Generamos el titulo con el nombre de la empresa de transporte
            title = {
                Text(text = "Ver: "+bus.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp)
            },
            text = {
                Row(
                    modifier = Modifier
                        .padding(5.dp)
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    TransportImage(bus)
                    Column(modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Ruta: " + bus.route, style = TextStyle(fontSize = 16.sp))
                        Text(text = "Buses: " + bus.quantity.toString(), style = TextStyle(fontSize = 16.sp))
                        Text(text = "Color: " + bus.color, style = TextStyle(fontSize = 16.sp))
                    }
                }
            },
            //El boton cierra el dialogo y vuelve falso el estado del dialogo
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }) {
                    Text("Aceptar",style = TextStyle(color = Color(myColorPrimary.toColorInt())))
                }
            },
            backgroundColor = Color(myColorBackground.toColorInt()),
            contentColor = Color(myColorPrimary.toColorInt())
        )
    }

}

//Metodo que permite graficar la imagen de acuerdo al codigo almacenado
@Composable
private fun TransportImage(bus: BusLine) {
    Image(
        painter = painterResource(id = bus.imageId),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(84.dp)
            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
    )
}



//fun writeNewUser(userId: String, name: String, email: String) {
//    val user = User(name, email)
//
//    database.child("users").child(userId).setValue(user)
//}