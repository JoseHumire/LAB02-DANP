package com.example.danp

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.danp.ui.theme.DANPTheme
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.MutableData
import com.google.firebase.database.Transaction
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ServerValue
import com.google.firebase.database.ktx.database

import com.google.firebase.ktx.Firebase

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

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
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
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
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
    Row(
        modifier = Modifier
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .padding(10.dp)
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.padding(7.dp)) {
            Spacer(modifier = Modifier.height(1.dp))
            Text(
                text = bus.name,
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color.DarkGray,
                    fontStyle = FontStyle.Italic
                )
            )
            Text(text = "Color: " + bus.color, style = TextStyle(fontSize = 15.sp))
            Text(text = "Ruta: " + bus.route, style = TextStyle(fontSize = 15.sp))
            Text(text = "Buses: " + bus.quantity.toString(), style = TextStyle(fontSize = 15.sp))
        }
        Column(modifier = Modifier.padding(7.dp)) {
            Button(onClick = {
                m_database.child("user").child("bus").setValue(bus.name)
                val intent = Intent(context, MapsActivity::class.java)
                intent.putExtra("bus_name", bus.name.toString())
                context.startActivity(intent)
            })
            { Text(text = "Ver ruta") }
        }
    }
}

//fun writeNewUser(userId: String, name: String, email: String) {
//    val user = User(name, email)
//
//    database.child("users").child(userId).setValue(user)
//}
