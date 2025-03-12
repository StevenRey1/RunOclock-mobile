package com.example.runoclock

import androidx.compose.ui.unit.times  // Import the times operator for Int * Dp
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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

@Composable
fun StatisticsScreen(navController: NavController) {
    val gridSpacing = 15.dp
    val textColor = Color(0xFF4A65FF) // Or use a suitable color from your theme
    // Trophy image (bottom)
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.image_19),
            contentDescription = "Trofeo",
            modifier = Modifier
                .height(12 * gridSpacing) // Adjust trophy size
                .width(12 * gridSpacing)
                .align(Alignment.BottomCenter),
            contentScale = ContentScale.Fit
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 2 * gridSpacing, vertical = 4 * gridSpacing) // Added padding
        ) {

            // Top Bar with "Inicio"
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(0.2 * gridSpacing),
                modifier = Modifier.padding(bottom = 1 * gridSpacing)
            ) {
                IconButton(onClick = {
                    navController.navigate("misRutinasScreen") {
                        popUpTo("misRutinasScreen") {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }) { // Use popBackStack to navigate back
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color(0XFFE66210)
                    )
                }
                Text(
                    "Inicio",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(color = 0XFFE66210)
                )
            }
            // Title
            Text(
                text = "Estadísticas",
                style = androidx.compose.material3.MaterialTheme.typography.headlineMedium.copy(
                    color = Color(0XFFE66210),
                ),
                modifier = Modifier
                    .padding(bottom = 2 * gridSpacing)
                    .fillMaxWidth() // Ensure title takes full width for center alignment
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )

            // Kilometraje Section
            StatisticItem(
                title = "Kilometraje",
                imageResId = R.drawable.image_17, // Replace with your actual image resource
                values = listOf(
                    "Mensual: 1.000",
                    "Promedio diario: 1.000",
                    "Anual: 1.000"
                ),
                textColor = textColor
            )

            // Ritmo Section
            StatisticItem(
                title = "Ritmo",
                imageResId = R.drawable.deporte, // Replace with your actual image resource
                values = listOf(
                    "Ritmo promedio: 1.000",
                    "Mejor ritmo: 1.000"
                ),
                textColor = textColor
            )

            // Objetivos Section
            StatisticItem(
                title = "Objetivos",
                imageResId = R.drawable.image_18, // Replace with your actual image resource
                values = listOf(
                    "Objetivos cumplidos: 1.000",
                    "Objetivos por cumplir:"
                ),
                listValues = listOf(
                    "1. Objetivo 1",
                    "2. Objetivo 2",
                    "3. Objetivo 3"
                ),
                textColor = textColor
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom, // Align content to the bottom
                horizontalArrangement = Arrangement.End // Align content to the right
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE64A19)),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .width(12 * gridSpacing)
                        .height(3 * gridSpacing)
                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "Crear objetivo",
                        tint = Color.White
                    )
                    Text("Crear objetivo", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.weight(1f)) // Push the button to the bottom
        }
    }
}

@Composable
fun StatisticItem(
    title: String,
    imageResId: Int,
    values: List<String>,
    listValues: List<String> = emptyList(),
    textColor: Color
) {
    val gridSpacing = 15.dp

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Image
        Column(
            modifier = Modifier
                .width(7.5 * gridSpacing)
                .height(7.5 * gridSpacing), // Take up the full width
            horizontalAlignment = Alignment.CenterHorizontally // Center contents horizontally
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = title,
                modifier = Modifier
                    .width(5 * gridSpacing)
                    .height(5 * gridSpacing)
            )
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = Color(0XFFE66210),
                textAlign = TextAlign.Center, // Center the text horizontally
                modifier = Modifier.padding(bottom = 0.5 * gridSpacing)
            )
        }
        // Text Column
        Column(modifier = Modifier.padding(start = 2 * gridSpacing)) {


            values.forEach { value ->
                Text(
                    text = value,
                    style = MaterialTheme.typography.labelLarge,
                    color = textColor,
                    modifier = Modifier.padding(bottom = 1 * gridSpacing)
                )
            }

            // List Values
            listValues.forEach { listValue ->
                Text(
                    text = listValue,
                    style = MaterialTheme.typography.bodyMedium,
                    color = textColor,
                    modifier = Modifier.padding(start = gridSpacing, bottom = 0.2 * gridSpacing)
                )
            }
        }
    }
    Divider(
        modifier = Modifier.padding(top = 0.5 * gridSpacing, bottom = 0.5 * gridSpacing),
        color = Color.Transparent
    ) // add space
}

@Preview(showBackground = true)
@Composable
fun StatisticsScreenPreview() {
    val navController = rememberNavController() // Need to pass a navController
    MaterialTheme {
        StatisticsScreen(navController = navController)
    }
}