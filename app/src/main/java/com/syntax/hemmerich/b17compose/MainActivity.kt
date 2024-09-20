package com.syntax.hemmerich.b17compose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.syntax.hemmerich.b17compose.ui.theme.B17ComposeTheme
import com.syntax.hemmerich.b17compose.composables.LazyExample
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            ExampleScaffold()
            //LazyExample(myNotes)
        }
    }

}

@Serializable
data class Note(
    val date : String,
    val desc : String
)


@Composable
fun NoteItem(note: Note, navController : NavController){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = note.date)
        Text(text = note.desc)
        Button(onClick = { navController.navigate(DetailScreen(note.desc))}) {
            
        }
    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ExampleScaffold(){
    val navController = rememberNavController()

    Scaffold(
        topBar = { myTopBar() },
        bottomBar = { myBottomBar()},
        floatingActionButton = { myFAB()},
        floatingActionButtonPosition = FabPosition.Start
    ) { contentPadding ->
        //LazyExample(notes = myNotes)
        NavHost(
            navController = navController,
            startDestination = HomeScreen,
            modifier = Modifier.padding(contentPadding).fillMaxSize()
            ){
            composable<HomeScreen>{ LazyExample(notes = myNotes,navController)}
            composable<DetailScreen> {
                val args = it.toRoute<DetailScreen>()
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                ) {
                        Text(text = args.desc)
                        Button(onClick = {navController.navigate(SettingsScreen)}) {
                            Text("To Settings")
                        }
                }

            }
            composable<SettingsScreen> { Text(text = "Settings") }
        }
    }
}
@Serializable
object HomeScreen

@Serializable
object SettingsScreen

@Serializable
data class DetailScreen(
    val desc : String
)

















@Composable
fun myFAB(){
    FloatingActionButton(onClick = { /*TODO*/ }) {
        Text(text = "X")
    }
}

@Composable
fun myBottomBar(){
    BottomAppBar(containerColor = MaterialTheme.colorScheme.tertiary) {
        Text(text = "Test")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun myTopBar(){
    TopAppBar(title = { Text(text = "Mein toller Title")}, modifier = Modifier)
}

val myNotes = listOf(
    Note("2024-06-27", "Rasen mähen"),
    Note("2024-06-28", "Müll rausbringen"),
    Note("2024-06-30", "Auto waschen"),
    Note("2024-07-02", "Blumen pflücken"),
)

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val myNotes = listOf(
        Note("2024-06-27", "Rasen mähen"),
        Note("2024-06-28", "Müll rausbringen"),
        Note("2024-06-30", "Auto waschen"),
        Note("2024-07-02", "Blumen pflücken"),
    )
    B17ComposeTheme {
        //LazyExample(myNotes)
        //HomeScreen()
        ExampleScaffold()
    }
}