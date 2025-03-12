package com.example.runoclock

import androidx.compose.ui.unit.times  // Import the times operator for Int * Dp
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.runoclock.R
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.window.Dialog
import com.example.runoclock.ui.theme.secondaryColor_0
import com.example.runoclock.ui.theme.tertiaryColor_0
@Composable
fun RoutineExecutionScreen(navController: NavController) {
    val gridSpacing = 15.dp
    var showTimePickerDialog by remember { mutableStateOf(false) }
    var showConfirmationDialog by remember { mutableStateOf(false) } // New state for pause confirmation

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.vector_9),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(10 * gridSpacing)
                .align(Alignment.TopCenter),
            contentScale = ContentScale.FillBounds
        )

        Image(
            painter = painterResource(id = R.drawable.vector_10),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(10 * gridSpacing)
                .align(Alignment.BottomCenter),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Rutina en ejecución",
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = Color(0xFFE64A19),

                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 7 * gridSpacing),
                textAlign = TextAlign.Center
            )

            Text(
                text = "Da clic sobre la alarma para ver su respectivo cronometro",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2 * gridSpacing, start = 2 * gridSpacing),
                textAlign = TextAlign.Start
            )

            val alarmItems = listOf(
                "Hidratación" to "Cronómetro",
                "Clima" to "Cronómetro",
                "Distancia" to "Contador"
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2 * gridSpacing),
                verticalArrangement = Arrangement.spacedBy(4 * gridSpacing)
            ) {
                items(alarmItems) { (name, description) ->
                    AlarmItem(name = name, description = description) {
                        showTimePickerDialog = true
                    }
                }
            }

            Button(
                onClick = { showConfirmationDialog = true }, // Show confirmation dialog on pause click
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE64A19)),
                modifier = Modifier
                    .width(13 * gridSpacing)
                    .padding(top = 5 * gridSpacing)
                    .height(4 * gridSpacing),
                shape = RoundedCornerShape(0.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.stop), // Replace with the actual drawable name
                    contentDescription = "Pause",
                    modifier = Modifier.size(15.dp) // Adjust the size as needed
                )
            }

            if (showTimePickerDialog) {
                TimePickerDialogExample { showTimePickerDialog = false } // Pass the setShowDialog function
            }

            if (showConfirmationDialog) {
                PauseConfirmationDialog(
                    onDismiss = { showConfirmationDialog = false },
                    onConfirm = {
                        showConfirmationDialog = false
                        navController.navigate("misRutinasScreen") // Navigate to MisRutinasScreen on "Yes"
                    }
                )
            }
        }
    }
}

@Composable
fun AlarmItem(name: String, description: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(5 * 15.dp)
            .clickable { onClick() }, // Execute the onClick function
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(1.dp, secondaryColor_0),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 1 * 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium,
                    color = tertiaryColor_0,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = tertiaryColor_0
                )
            }
            Image(
                painter = painterResource(id = R.drawable.image_23),
                contentDescription = null,
                modifier = Modifier
                    .width(3 * 15.dp)
                    .height(3 * 15.dp)
            )
        }
    }
}

@Composable
fun TimePickerDialogExample(setShowDialog: () -> Unit) {
    AlertDialog(
        onDismissRequest = {
            setShowDialog()
        },
        title = {
            Text(
                "Cronómetro Alarma Hidratación",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
        },
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth() //Ensures the content takes the available width
            ) {
                // Hour/Minute selector placeholders (Replace with your actual selector)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center, //Centers content in Row
                    modifier = Modifier.fillMaxWidth() //Ensures Row takes the available width

                ) {
                    Box(
                        modifier = Modifier.padding(15.dp),
                        contentAlignment = Alignment.Center //Centers content in Box
                    ) {
                        Text(
                            "20",
                            style = MaterialTheme.typography.displayMedium
                        ) // Replace with actual hour selection
                    }
                    Text(":", style = MaterialTheme.typography.displayMedium)
                    Box(
                        modifier = Modifier.padding(15.dp),
                        contentAlignment = Alignment.Center //Centers content in Box
                    ) {
                        Text(
                            "00",
                            style = MaterialTheme.typography.displayMedium
                        ) // Replace with actual minute selection
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()

                ) {
                    Box(
                        modifier = Modifier.padding(15.dp),
                        contentAlignment = Alignment.Center //Centers content in Box
                    ) {
                        Text(
                            "Hour",
                            style = MaterialTheme.typography.bodySmall
                        )// Replace with actual hour selection
                    }

                    Box(
                        modifier = Modifier.padding(15.dp),
                        contentAlignment = Alignment.Center //Centers content in Box
                    ) {
                        Text(
                            "Minute",
                            style = MaterialTheme.typography.bodySmall
                        ) // Replace with actual minute selection
                    }
                }

            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    setShowDialog()
                }
            ) {
                Text("OK", color = Color(color = 0XFFE66210))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    setShowDialog()
                }
            ) {
                Text("Cancel", color = Color.Gray)
            }
        },

        )
}

@Composable
fun PauseConfirmationDialog(onDismiss: () -> Unit, onConfirm: () -> Unit) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            shape = RoundedCornerShape(0.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Ha pausado la rutina",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 20.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "¿Desea cancelarla?",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 45.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { onDismiss() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE66210)),
                        shape = RoundedCornerShape(0.dp),
                        modifier = Modifier
                            .width(120.dp)
                            .height(80.dp)
                    ) {
                        Text(
                            "No",
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }

                    Button(
                        onClick = { onConfirm() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE66210)),
                        shape = RoundedCornerShape(0.dp),
                        modifier = Modifier
                            .width(120.dp)
                            .height(80.dp)
                    ) {
                        Text(
                            "Si",
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RoutineExecutionScreenPreview() {
    val navController = rememberNavController()
    androidx.compose.material3.MaterialTheme {
        RoutineExecutionScreen(navController = navController)
    }
}