package com.example.moviecounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviecounter.ui.theme.MovieCounterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieCounterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UserRegistrationForm(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun UserRegistrationForm(modifier: Modifier = Modifier) {
    var userCount by rememberSaveable { mutableStateOf(0) }
    var userName by rememberSaveable { mutableStateOf("") }
    var userDni by rememberSaveable { mutableStateOf("") }
    var userPassword by rememberSaveable { mutableStateOf("") }
    var registrationMap by rememberSaveable { mutableStateOf(mutableMapOf<String, Int>()) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Yellow)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondohuella),
            contentDescription = "Logo",
            modifier = Modifier.size(300.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Total de usuarios registrados: $userCount",
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text("Nombre") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = userDni,
            onValueChange = { userDni = it },
            label = { Text("DNI") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = userPassword,
            onValueChange = { userPassword = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (userName.isNotBlank() && userDni.isNotBlank() && userPassword.isNotBlank()) {
                userCount++

                val currentCount = registrationMap[userDni] ?: 0
                registrationMap[userDni] = currentCount + 1

                userName = ""
                userDni = ""
                userPassword = ""
            }
        }) {
            Text("Register User")
        }

        Spacer(modifier = Modifier.height(16.dp))

        registrationMap.forEach { (dni, count) ->
            Text(
                text = "DNI: $dni se ha registrado $count veces",
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserRegistrationForm() {
    UserRegistrationForm()
}
