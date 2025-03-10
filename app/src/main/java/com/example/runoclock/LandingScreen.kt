package com.example.runoclock
import androidx.compose.animation.animateColorAsState
import androidx.compose.ui.unit.times  // Import the times operator for Int * Dp
import androidx.compose.ui.unit.dp // Make sure to import the dp extension
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.runoclock.ui.theme.primaryColor_0

@Composable
fun LandingScreen(navController: NavController) {
    Image(
        painter = painterResource(id = R.drawable.ciudad),
        contentDescription = "App Illustration",
        modifier = Modifier
            .fillMaxWidth()
            .height(19 * 15.dp)
            .padding(top = 15.dp * 4),
        contentScale = ContentScale.Crop
    )
    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Image(
            painter = painterResource(id = R.drawable.rectangle_14),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10 * 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Shoe Icon",
                modifier = Modifier
                    .width(8 * 15.dp)
                    .height(7 * 15.dp),
                contentScale = ContentScale.Fit,
            )

            Text(
                text = "Run O'clock",
                style = MaterialTheme.typography.displayMedium.copy(
                    fontSize = 32.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.5.sp
                ),
                modifier = Modifier.padding(bottom = 32.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(10 * 15.dp))


            MyButtonWithHover(navController = navController)

            Spacer(modifier = Modifier.height(6 * 15.dp))

            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(containerColor = primaryColor_0),
                modifier = Modifier
                    .width(25 * 15.dp)
                    .height(4 * 15.dp),
                shape = RoundedCornerShape(0.dp)
            ) {
                Text(
                    text = "Únete Gratis",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineMedium.copy(),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}


@Composable
fun MyButtonWithHover(navController: NavController) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    // Make sure hoverColor is defined and of type Color
    val hoverColor = primaryColor_0.copy(alpha = 0.8f)

    val buttonColor by animateColorAsState(
        targetValue = if (isHovered) hoverColor else primaryColor_0,
        label = "ButtonColorAnimation"
    )


    Button(
        onClick = {
            navController.navigate("loginScreen")
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor
        ),
        modifier = Modifier
            .width(25 * 15.dp)
            .height(4 * 15.dp),
        shape = RoundedCornerShape(0.dp),
        interactionSource = interactionSource // Attach the interaction source
    ) {
        Text(
            text = "Iniciar Sesión",
            color = Color.White,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium.copy()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LandingScreenPreview() {
    MaterialTheme {
        // Pass a dummy NavController for Preview
        LandingScreen(navController = androidx.navigation.compose.rememberNavController())
    }
}