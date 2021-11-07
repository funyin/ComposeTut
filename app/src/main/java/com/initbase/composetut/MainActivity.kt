package com.initbase.composetut

import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.initbase.composetut.ui.theme.ComposeTutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutTheme {
                // A surface container using the 'background' color from the theme
                MyApp()
            }
        }
    }

    @Preview(uiMode = UI_MODE_NIGHT_YES, name = "Map app (Dark Mode)")
    @Preview
    @Composable
    fun MyApp() {
        Surface(color = MaterialTheme.colors.background) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    bitmap = ImageBitmap.imageResource(R.drawable.myportraitcropped),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier.padding(
                        horizontal = 16.dp,
                        vertical = 24.dp
                    )
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Foo Mongers",
                            modifier = Modifier.padding(end = 12.dp),
                            style = TextStyle(
                                color = Color.Green
                            )
                        )
                        Text(
                            text = "$5.99", style = TextStyle(
                                fontSize = 14.sp,
                            )
                        )
                    }
                    val names: List<String> = List(50) { "Funyinoluwa" }
                    LazyColumn(
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                            .weight(1f)
                    ) {
                        items(count = names.size) { index ->
                            Greeting(
                                name = names[index],
                                modifier = Modifier.padding(vertical = 4.dp),
                                index = index
                            )
                        }
                    }
                    TextButton(
                        onClick = {
                            finish()
                        }, modifier = Modifier
                            .align(Alignment.End)
                    ) {
                        Text("Back to onboarding")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Greeting(modifier: Modifier = Modifier, name: String = "Funyinoluwa", index: Int = 0) {
    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    val color by animateColorAsState(targetValue = if (expanded) MaterialTheme.colors.primary else MaterialTheme.colors.secondary)
    Card(backgroundColor = color, modifier = modifier) {
        Row(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            Spring.StiffnessLow
                        )
                    )
            ) {
                Text(text = "Welcome to my space $name")
                Text(
                    text = "$index", style = MaterialTheme.typography.h3.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                if (expanded) {
                    Text(
                        text = ("Composem ipsum color sit lazy, padding theme elit, sed do bouncy. ").repeat(
                            4
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            IconButton(onClick = { expanded = !expanded }) {

                Icon(
                    imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (expanded) stringResource(R.string.contentDescriptionExpanded) else stringResource(
                        R.string.contentDescriptionCollapsed
                    )
                )
            }
        }
    }
}