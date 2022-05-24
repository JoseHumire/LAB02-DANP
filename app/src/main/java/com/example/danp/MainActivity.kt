package com.example.danp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DistrictsList()
        }
    }
}

@Preview
@Composable
fun DistrictsList() {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            TitleList("Distritos de Arequipa")
        }
        items(districtsList) {
            DistrictItemList(district = it)
        }
    }
}

@Composable
fun DistrictItemList(district: District) {
    val context = LocalContext.current
    Card(
        modifier = Modifier.padding(horizontal = 2.dp, vertical = 2.dp).fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Color(myColorBackground.toColorInt()),
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .padding(10.dp),

            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = district.name,
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color(myColorPrimary.toColorInt()),
                    fontStyle = FontStyle.Italic
                    //fontWeight = FontWeight.Medium
                ),
            )
            Button(
                onClick = {
                    val intent = Intent(context, BusesActivity::class.java)
                    intent.putExtra("district_code", district.code)
                    context.startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(myColorSecond.toColorInt()))
            ){
                Text(text = "Ver buses", style = TextStyle(color = Color(myColorBackground.toColorInt())))
            }
        }
    }
}

@Composable
fun TitleList(title: String) {
    Box(
        modifier = Modifier.padding(20.dp, 5.dp).padding(10.dp, 0.dp)
    ) {
        Text(
            text = title,
            style = TextStyle(
                color = Color.DarkGray,
                fontSize = 28.sp,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center
            )
        )

    }
}


