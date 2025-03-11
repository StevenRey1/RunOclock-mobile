package com.example.runoclock

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.Dp
import com.example.runoclock.R
import androidx.compose.ui.unit.times
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

import androidx.navigation.NavController
import androidx.compose.ui.window.Dialog
import androidx.navigation.compose.rememberNavController

import com.example.runoclock.ui.theme.primaryColor_0
import com.example.runoclock.ui.theme.secondaryColor_10
import com.example.runoclock.ui.theme.secondaryColor_20
import com.example.runoclock.ui.theme.tertiaryColor_60




// Assuming you have a LandingScreen route defined somewhere (e.g., in a Navigation.kt file)
const val LANDING_SCREEN_ROUTE = "landingScreen"  // Replace with your actual route

@Composable
fun UnderlinedText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = androidx.compose.material3.MaterialTheme.colorScheme.primary,
    navController: NavController
) {
    androidx.compose.material3.Text(
        text = text,
        modifier = modifier
            .clickable {
                // Navigate to the LandingScreen when clicked
                navController.navigate(LANDING_SCREEN_ROUTE) {
                    popUpTo(0) {
                        inclusive = true
                    } // Clear backstack
                }
            },
        color = secondaryColor_10,
        textDecoration = TextDecoration.Underline,
        style = androidx.compose.material3.MaterialTheme.typography.titleMedium.copy()
    )
}

@Composable
fun MisRutinasScreen(navController: NavController) {
    val gridSpacing = 15.dp // Define grid spacing
    var showPlayDialog by remember { mutableStateOf(false) } // Add state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 15.dp * 4),
        horizontalAlignment = Alignment.Start // Align content to the left

    ) {
        // Title
        androidx.compose.material3.Text(
            text = "Mis rutinas",
            style = androidx.compose.material3.MaterialTheme.typography.headlineMedium.copy(
                color = primaryColor_0,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .padding(top = 1 * gridSpacing, bottom = 2 * gridSpacing)
                .fillMaxWidth() // Ensure title takes full width for center alignment
                .wrapContentWidth(Alignment.CenterHorizontally) // Center the title within its space
        )

        // Routine List
        val routines = listOf("Rutina 1", "Rutina 2", "Rutina 3", "Rutina 4")
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()

                .padding(horizontal = 2 * gridSpacing) // Adjusted horizontal padding
        ) {
            itemsIndexed(routines) { index, routine ->
                RoutineItem(
                    index + 1,
                    routine,
                    gridSpacing,
                    onPlayClick = { showPlayDialog = true; },
                ) // Add onPlayClick
                if (index < routines.size - 1) {
                    Divider(color = Color.LightGray, thickness = 1.dp)
                }
            }
        }

        // Create Routine Button
        androidx.compose.material3.Button(
            onClick = { /*TODO*/ },
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = Color(0xFFE64A19)),
            shape = RoundedCornerShape(0.dp),  //Aligned with gridSpacing
            modifier = Modifier.padding(start = 2 * gridSpacing) // Aligned with gridSpacing
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Crear rutina", tint = Color.White)
            androidx.compose.material3.Text("Crear rutina", color = Color.White, style =androidx.compose.material3.MaterialTheme.typography.titleMedium.copy(

            ))
        }

        // Statistics and Races Icons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 4 * gridSpacing,
                    bottom = 2 * gridSpacing,
                    start = 2 * gridSpacing,
                    end = 2 * gridSpacing
                ), // Aligned padding
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable {
                    navController.navigate("staticsScreen") // Navigate to StatisticsScreen
                },
                verticalArrangement = Arrangement.spacedBy(4.dp) // Added spacing between image and text
            ) {
                Image(
                    painter = painterResource(id = R.drawable.statics),
                    contentDescription = "Estadísticas",
                    modifier = Modifier
                        .width(70.dp)
                        .height(70.dp),
                    contentScale = ContentScale.Fit // Added ContentScale
                )
                androidx.compose.material3.Text(
                    "Estadísticas",
                    style = androidx.compose.material3.MaterialTheme.typography.titleMedium
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable { /*TODO*/ },
                verticalArrangement = Arrangement.spacedBy(4.dp) // Added spacing between image and text
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_races),
                    contentDescription = "Carreras",
                    modifier = Modifier
                        .width(70.dp)
                        .height(70.dp),
                    contentScale = ContentScale.Fit // Added ContentScale
                )
                androidx.compose.material3.Text(
                    "Carreras",
                    style = androidx.compose.material3.MaterialTheme.typography.titleMedium
                )
            }
        }

        Box( //Changed Row to Box to allow for absolute positioning
            modifier = Modifier
                .fillMaxWidth()
                .height(16 * gridSpacing) //Height the image goes
        ) {
            Image(
                painter = painterResource(id = R.drawable.pngwing_com_1),
                contentDescription = "Carreras",
                modifier = Modifier
                    .width(10 * gridSpacing)
                    .height(16 * gridSpacing)
                    .align(Alignment.BottomStart), //Align Bottom Start to make the position static at the bottom
                contentScale = ContentScale.Fit // Added ContentScale
            )

            // Log Out
            UnderlinedText(
                text = "Cerrar sesión",
                modifier = Modifier

                    .padding(start = 10 * gridSpacing, top = 4 * gridSpacing),
                color = Color.Blue,
                navController = navController // Pass the navController here
            )
        }
    }
    // Now Displaying the RutineAlertDialog
    if (showPlayDialog) {
        Dialog(onDismissRequest = { showPlayDialog = false }) {
            PlayRoutineAlert(navController = navController)
        }
    }

}

@Composable
fun RoutineItem(index: Int, routineName: String, gridSpacing: Dp, onPlayClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 1 * gridSpacing, vertical = 0.5 * gridSpacing),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        androidx.compose.material3.Text(
            "$index. $routineName",
            color = secondaryColor_20,
            style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            androidx.compose.material3.IconButton(
                onClick = onPlayClick,
                modifier = Modifier.size(48.dp) // Increased IconButton size
            ) {
                Icon(
                    Icons.Filled.PlayArrow,
                    contentDescription = "Play",
                    tint = secondaryColor_10,
                    modifier = Modifier
                        .size(36.dp) // Increased Icon size
                        .border(
                            width = 1.dp,
                            color = primaryColor_0,
                            shape = RoundedCornerShape(45.dp)
                        )
                )
            }


            androidx.compose.material3.Button(
                onClick = { /*TODO*/ },
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(30.dp),
                border = BorderStroke(1.dp, primaryColor_0), // Add border here
                modifier= Modifier.padding(start=15.dp)

            ) {
                Icon(
                    Icons.Filled.Edit,
                    contentDescription = "Editar",
                    tint = tertiaryColor_60,
                    modifier = Modifier
                        .padding(end = 15.dp)
                )
                androidx.compose.material3.Text(
                    "Editar",
                    color = tertiaryColor_60,
                    style = androidx.compose.material3.MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MisRutinasScreenPreview() {
    val navController = rememberNavController() // Create a NavController for the preview
    MaterialTheme {
        MisRutinasScreen(navController = navController)
    }
}