package com.example.projekt1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projekt1.ui.theme.Projekt1Theme
// Wersja z guzikiem i text-field na 3.0
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Projekt1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingWithInput()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreetingWithInput() {
    var inputText by remember { mutableStateOf("") }
    var displayedText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(20.dp)
    ) {
        TextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Label") }
        )

        Button(
            onClick = {
                // Update the displayed text when the button is clicked
                displayedText = inputText
            },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Show Text")
        }

        // Display the text
        Greeting(name = displayedText)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Projekt1Theme {
        GreetingWithInput()
    }
}
