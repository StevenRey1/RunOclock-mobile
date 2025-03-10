package com.example.runoclock

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.MaterialTheme
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun AccountScreen(
    navController: NavController,
    name: String = "Jhon Doe",
    email: String = "JhonDoeExample@example.com"
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(640.dp)
            .background(Color.White)
            .padding(16.dp), // Apply padding to Box
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally // Ensure column is horizontally centered
        ) {
            Text(
                text = "Elige una cuenta",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.Black
                ),
                modifier = Modifier.padding(bottom = 60.dp)

            )

            Spacer(modifier = Modifier.height(16.dp))

            AccountItem(
                name = name,
                email = email,
                icon = painterResource(id = R.drawable.person) ,// Replace with your actual icon resource
                onClick = { navController.navigate("misRutinasScreen") }
            )

            Divider(
                color = Color(0xFFF7941D),
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            NewAccountItem(
                label = "Create user",
                icon = painterResource(id = R.drawable.user_plus), // Replace with your actual icon resource
            )

            Divider(
                color = Color(0xFFF7941D),
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Composable
fun AccountItem(
    name: String,
    email: String,
    icon: Painter,
    onClick: () -> Unit // <-- Add onClick
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },  // <-- Make Row clickable
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = "Account Icon",
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = name,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            )
            Text(
                text = email,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Color.Black
                )
            )
        }
    }
}

@Composable
fun NewAccountItem(
    label: String,
    icon: Painter
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = "New Account Icon",
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = label,
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = Color.Black
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AccountScreenPreview() {
    MaterialTheme {
        AccountScreen(navController = rememberNavController())
    }
}