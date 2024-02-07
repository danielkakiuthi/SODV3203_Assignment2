package com.example.sodv3203_assignment2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sodv3203_assignment2.ui.theme.SODV3203_Assignment2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SODV3203_Assignment2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DisplayLayout()
                }
            }
        }
    }
}

@Composable
fun ShowImage(drawableResId: Int, modifier:Modifier=Modifier) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier= modifier
            .padding(vertical = 30.dp)
            .border(BorderStroke(3.dp, Color.DarkGray))

    ) {
        Image(
            painter = painterResource(id = drawableResId),
            contentDescription = "Image",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(10.dp)
        )
    }

}

@Composable
fun ShowInformation(artworkTitle: String, artworkArtist: String, year: String, modifier:Modifier=Modifier) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier=modifier
            .background(Color(red = 237, green = 235, blue = 245))
            .fillMaxWidth()
            .padding(10.dp)
            .defaultMinSize(minHeight = 400.dp)

    ) {
        Text(
            text = artworkTitle,
            fontSize = 25.sp,
            lineHeight = 30.sp
        )
        Row(
            modifier = Modifier
        ) {
            Text(
                text = artworkArtist,
                fontWeight = FontWeight.Bold,
                lineHeight = 20.sp
            )
            Text(
                text = " (${year})"
            )
        }
    }
}

@Composable
fun ShowButtons(currentState: Int, onPreviousClick: (Int)->Unit, onNextClick: (Int)->Unit, modifier:Modifier=Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(red = 72, green = 92, blue = 146),
                contentColor = Color.White
            ),
            onClick = { onPreviousClick(currentState) }
        ) {
            Text(
                text = "Previous",
                textAlign = TextAlign.Center,
                modifier=Modifier.width(60.dp)
            )
        }
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(red = 72, green = 92, blue = 146),
                contentColor = Color.White
            ),
            onClick = { onNextClick(currentState) }
        ) {
            Text(
                text = "Next",
                textAlign = TextAlign.Center,
                modifier = Modifier.width(60.dp)
            )
        }
    }
}

@Composable
fun DisplayLayout(modifier: Modifier = Modifier) {
    var currentState by remember { mutableIntStateOf(0) }
    val drawableResId: Int;
    val artworkTitle: String;
    val artworkArtist: String;
    val year: String;

    when(currentState) {
        0 -> {
            drawableResId = R.drawable.pinkfloyd_thedarksideofthemoon_1973;
            artworkTitle = "The Dark Side Of The Moon";
            artworkArtist = "Pink Floyd";
            year = "1973";
        }
        1 -> {
            drawableResId = R.drawable.thebeatles_abbeyroad_1969;
            artworkTitle = "Abbey Road";
            artworkArtist = "The Beatles";
            year = "1969";
        }
        2 -> {
            drawableResId = R.drawable.talkingheads_speakingintongues_1983;
            artworkTitle = "Talking Heads";
            artworkArtist = "Speaking In Tongues";
            year = "1983";
        }
        else -> {
            drawableResId = androidx.core.R.drawable.ic_call_decline;
            artworkTitle = "[Debug] Something went wrong";
            artworkArtist = "[Debug] Something went wrong";
            year = "[Debug] Something went wrong";
        }
    }


    Column(
        modifier = modifier
            .padding(50.dp)
            .verticalScroll(state = rememberScrollState())

    ) {
        ShowImage(
            drawableResId = drawableResId,
            modifier = modifier
                .weight(2f)
        );
        ShowInformation(
            artworkTitle = artworkTitle,
            artworkArtist = artworkArtist,
            year = year,
            modifier = modifier
                .weight(0.5f)
                .fillMaxWidth()
        );
        ShowButtons(
            currentState = currentState,
            onPreviousClick = {currentState = Math.floorMod(it-1, 3)},
            onNextClick = {currentState = Math.floorMod(it+1, 3)},
            modifier = modifier
                .weight(0.5f)
                .fillMaxWidth()
                .height(100.dp)
        );
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SODV3203_Assignment2Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            DisplayLayout()
        }
    }
}