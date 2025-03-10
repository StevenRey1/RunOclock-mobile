package com.example.runoclock

import androidx.compose.ui.unit.times  // Import the times operator for Int * Dp
import androidx.compose.ui.unit.dp // Make sure to import the dp extension
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.runoclock.ui.theme.primaryColor_0

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OTPCodeScreen(
    navController: NavController,
    email: String = "Example@example.com",
    onVerify: (String) -> Unit = {},
    onResend: () -> Unit = {}
) {
    val otpLength = 6
    var otpValues by remember { mutableStateOf(List(otpLength) { "" }) }
    val focusRequesters = remember { List(otpLength) { FocusRequester() } }
    var resendMessageVisible by remember { mutableStateOf(false) }  // State for the message

    // Grid Parameters
    val gridSpacing = 15.dp
    val columnMargin = 20.dp
    val numberOfColumns = 5

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = columnMargin) // Apply column margin
                .padding(top = 105.dp),  // Adjusted top padding to match the grid
            horizontalAlignment = Alignment.Start, // Alinea todo a la izquierda
            verticalArrangement = Arrangement.spacedBy(gridSpacing)  // use grid for spacing
        ) {
            Text(
                text = "Te hemos enviado un código al correo $email",
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    fontSize = 22.sp,
                    lineHeight = 28.sp
                ),
                color = Color.Black,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .padding(bottom = 9 * gridSpacing)
                    .fillMaxWidth()  // Hace que el texto ocupe todo el ancho disponible
            )

            Text(
                text = "Ingresa el código",
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    fontSize = 22.sp,
                    lineHeight = 28.sp
                ),
                color = Color.Black,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(bottom = 1 * gridSpacing)
                    .fillMaxWidth() //Hace que el texto ocupe todo el ancho disponible
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(15.dp), // Separate the boxes 15dps
                modifier = Modifier.fillMaxWidth(), // take all width
            ) {
                for (i in 0 until otpLength) {
                    OutlinedTextField(
                        value = otpValues[i],
                        onValueChange = { newValue ->
                            if (newValue.length <= 1 && newValue.all { it.isDigit() }) {
                                otpValues = otpValues.toMutableList().apply { this[i] = newValue }
                                if (newValue.isNotEmpty()) {
                                    if (i < otpLength - 1) {
                                        focusRequesters[i + 1].requestFocus()
                                    }
                                } else {
                                    if (i > 0) {
                                        focusRequesters[i - 1].requestFocus()
                                    }
                                }
                            }
                        },
                        modifier = Modifier
                            .width(45.dp)  // adjust width
                            .focusRequester(focusRequesters[i]),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        shape = RoundedCornerShape(4.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = primaryColor_0,
                            unfocusedBorderColor = primaryColor_0,
                            cursorColor = Color.Black
                        )
                    )
                    LaunchedEffect(Unit) {
                        if (i == 0) focusRequesters[i].requestFocus()
                    }
                }
            }

            // Container for the "Continuar" button to align it to the right
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End, // Aligns the button to the end (right)
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = {
                        val otpCode = otpValues.joinToString("")
                        onVerify(otpCode)
                        navController.navigate("misRutinasScreen")
                    },
                    modifier = Modifier.padding(top = 1 * gridSpacing) // Space from the OTP fields
                ) {
                    Text(
                        text = "Continuar",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black
                    )
                }
            }

            // Center the "Obtener otro cÃ³digo" button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.5 * gridSpacing), // Reduced top padding
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        onResend() // Perform resend action if needed
                        resendMessageVisible = true // Set message visibility to true
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = primaryColor_0)
                ) {
                    Text(
                        text = "Obtener otro código",
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.White
                    )
                }
            }

            // Show message here
            if (resendMessageVisible) {
                Text(
                    text = "Se ha reenviado un nuevo código a tu correo electrónico",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 1 * gridSpacing)
                )
            }
        }

        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            val orangeCircleRadius = canvasWidth * 0.50f //Increased
            val blueCircleRadius = canvasWidth * 0.25f //Increased

            val orangeCircleOffset = Offset(canvasWidth * 1.0f, canvasHeight)
            val blueCircleOffset = Offset(canvasWidth * 0.55f, canvasHeight) // Moved to the left

            drawCircle(
                color = Color(0xFF7B9EFF),
                radius = blueCircleRadius,
                center = blueCircleOffset
            )

            drawCircle(
                color = Color(0xFFE66210),
                radius = orangeCircleRadius,
                center = orangeCircleOffset
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OTPCodeScreenPreview() {
    MaterialTheme {
        OTPCodeScreen(navController = rememberNavController())
    }
}