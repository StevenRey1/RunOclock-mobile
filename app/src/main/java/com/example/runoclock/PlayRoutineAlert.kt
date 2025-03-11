package com.example.runoclock

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow // <-- Import PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.runoclock.ui.theme.primaryColor_0

@Composable
fun PlayRoutineAlert(
    navController: NavController // Now you need the NavController
) {
    Box(
        modifier = Modifier
            .fillMaxWidth() //Take the box with all width and height
            .height(320.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center //Align element in the center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, // Centraliza elementos en la columna
            verticalArrangement = Arrangement.Center //Justify content
        ) {
            Text(
                text = "¿Desea iniciar la rutina?", // The title for the modal
                color = Color.Black,
                style = MaterialTheme.typography.headlineSmall.copy()
            )
            Spacer(modifier = Modifier.height(75.dp)) //Space between the title and the button
            Button(
                onClick = {
                    navController.navigate("routineExecutionScreen") // Take the user to the home screen (remember to build it)
                },
                modifier = Modifier
                    .width(165.dp)
                    .height(135.dp),
                shape = RoundedCornerShape(0.dp),

                colors = ButtonDefaults.buttonColors(containerColor = primaryColor_0)  //Set the play button color
            ) {
                Icon(Icons.Filled.PlayArrow, contentDescription = "Play", tint = Color.White,
                    modifier = Modifier
                        .size(30.dp)  )
                Text(
                    text = "Play",
                    style = MaterialTheme.typography.titleLarge.copy()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlayRoutineAlertPreview() {
    MaterialTheme {
        PlayRoutineAlert(navController = rememberNavController())
    }
}