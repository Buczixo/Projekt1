package com.example.projekt1

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.*
import com.example.projekt1.ui.theme.Projekt1Theme
// Wersja z trzema ekranami i przerzucaniem danych
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Projekt1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Nav()
                }
            }
        }
    }
}

@Composable
fun Nav(){
    val navCont = rememberNavController()
    NavHost(navController = navCont, startDestination = "A"){
        composable(route = "A"){
            GreetingWithInput(onNavigateToScreen2 = {
                navCont.navigate("second_screen/$it")
            })
        }
        composable(
            "second_screen/{param}", arguments = listOf
                (navArgument("param"){
                type = NavType.StringType
            }))
        {
            val param = it.arguments?.getString("param") ?:""
            EkranDrugi(param = param, navCont )
        }
        composable("third_screen"){
            EkranTrzeci(navCont)
        } }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreetingWithInput(onNavigateToScreen2: (String) -> Unit) {
    var textFieldState by remember {
        mutableStateOf(" ")
    }
    var inputText by remember { mutableStateOf("") }
    var displayedText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),

        verticalArrangement = Arrangement.spacedBy(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Wpisz dane") }
        )

        Button(
            onClick = {
                // Update the displayed text when the button is clicked
                displayedText = inputText
            },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Zmień tekst")
        }

        Greeting(name = displayedText)

        TextField(value = textFieldState, label = {
            Text("Dane dla kolejnej strony :")
        }, onValueChange = {
            textFieldState = it
        }, singleLine = true, modifier = Modifier.fillMaxWidth())
        Button(onClick = { onNavigateToScreen2(textFieldState) }) {
            Text(text = "Przejdź na drugą stronę światła")
        }

        // Display the text

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Witam w apce! Podaj dane w pierwszym polu ! $name!",
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Projekt1Theme {
        Nav()
    }
}

@Composable
fun  EkranDrugi(param: String, navController: NavController){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),

        verticalArrangement = Arrangement.spacedBy(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Witaj na ekranie 2",
            modifier = Modifier.clickable {  },
        )
        Text(
            text = param,
        )
        Button(onClick = { navController.navigate("third_screen") }) {
            Text(text = "Idz do ekranu nr. 3")
        }
    }
}

@Composable
fun  EkranTrzeci(navController: NavController){
    val context = LocalContext.current
    Column (
        Modifier.fillMaxSize()
            .background(Color.Blue),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Text(
            text = "Ekran trzeci i ostatni"
        )
        Button(onClick = { navController.navigate("A") }) {
            Text(text = "Wróć na ekran główny")
        }

        Button(onClick = { Toast.makeText(context, "A może tościka?", Toast.LENGTH_SHORT).show()}
        ) {
            Text(text = "Toster")
        }
    }
}
