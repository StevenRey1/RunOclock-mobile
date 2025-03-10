package com.example.runoclock

import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.unit.times  // Import the times operator for Int * Dp
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.window.Dialog

import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.runoclock.ui.theme.primaryColor_0
import com.example.runoclock.ui.theme.primaryColor_60
import com.example.runoclock.ui.theme.tertiaryColor_0

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {

    val gridSpacing = 15.dp
    val columnMargin = 20.dp

    // State to control the visibility of the Dialog
    var showAccountScreen by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {

        // Background SVG (place BEFORE the column)
        Image(
            painter = painterResource(id = R.drawable.vector_4),
            contentDescription = null,
            modifier = Modifier
                .width(gridSpacing * 21)
                .height(gridSpacing * 14) // Specific height (210dp = 14 * 15)
                .align(Alignment.TopStart),

            contentScale = ContentScale.FillBounds
        )


        // Bottom image with similar dimensions.
        Image(
            painter = painterResource(id = R.drawable.vector_5),
            contentDescription = null,
            modifier = Modifier
                .width(gridSpacing * 21)
                .height(gridSpacing * 14) // Fills the width of the screen
                .align(Alignment.BottomEnd) // Align to the bottom Center
                .padding(bottom = 0.dp), // You can adjust this if the position is not perfect.
            contentScale = ContentScale.FillBounds // Important to stretch across the bottom
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = columnMargin),
            verticalArrangement = Arrangement.spacedBy(gridSpacing)
        ) {

            Text(
                text = "Iniciar sesión en\nRun O'clock",
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center,
                    color = primaryColor_0
                ),
                modifier = Modifier.padding(top = 7 * gridSpacing, start = 6 * gridSpacing)
            )

            var email by remember { mutableStateOf("Example@example.com") }

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = {
                    androidx.compose.material3.Text(
                        text = "Correo electrónico",
                        style = MaterialTheme.typography.bodyLarge,
                        color = tertiaryColor_0// Apply the background color here
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4 * 15.dp),
                shape = RoundedCornerShape(4.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor_0,
                    unfocusedBorderColor = primaryColor_0,
                    cursorColor = Color.Black
                )
            )

            Button(
                onClick = {
                    navController.navigate("otpCodeScreen")
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(0.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = gridSpacing * 3)
                    .padding(top = gridSpacing * 4),

                contentPadding = PaddingValues(16.dp),
                border = BorderStroke(1.dp, primaryColor_60),
            ) {
                Text(
                    text = "Continuar",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = Color(0xFFF7941D),
                    ),
                )
            }

            androidx.compose.material.Divider(
                color = Color(0xFFF7941D), thickness = 1.dp,
                modifier = Modifier.padding(bottom = 3 * gridSpacing)
            )

            Button(
                onClick = {
                    showAccountScreen = true // Open the Dialog
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(0.dp),
                contentPadding = PaddingValues(16.dp),
                border = BorderStroke(1.dp, primaryColor_60),
            ) {
                Text(
                    text = "G Continua con Google",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = Color(0xFFF7941D),

                        ),
                )
            }
        }

    }

    // Show the Account selection Dialog
    if (showAccountScreen) {
        Dialog(onDismissRequest = { showAccountScreen = false }) {
            AccountScreen(navController = navController, email= "jhonDoeExample@example.com") // Display content on the screen
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    MaterialTheme {
        LoginScreen(navController = rememberNavController())
    }
}