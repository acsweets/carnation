package com.example.carnation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.carnation.ui.theme.CarnationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarnationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                    ArtistCard()
                }
            }
        }
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
fun ArtistCard() {
    Column{
        Text("Alfred Sisley")
        Text("3 minutes ago")
        WithConstraintsComposable()
        FlowRowSimpleUsageExample()
    }

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FlowRowSimpleUsageExample() {
    FlowRow(
        modifier = Modifier.padding(8.dp) ,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text("3 minutes ago")
        Text("3 minutes ago")
        Text("3 minutes ago")
        Box(modifier = Modifier.padding(8.dp).height(200.dp).background(color = Color.Blue)){
            Text("3 minutes ago")


        }
    }
}

@Composable
fun WithConstraintsComposable() {
    BoxWithConstraints {
        Text("My minHeight is $minHeight while my maxWidth is $maxWidth")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CarnationTheme {
        Greeting("Android")
    }
}