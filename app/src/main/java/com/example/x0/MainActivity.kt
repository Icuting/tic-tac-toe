package com.example.x0

import android.graphics.Paint.Align
import android.media.AsyncPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.x0.ui.theme.X0Theme
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            X0Theme {
                var movePlayer by remember { mutableStateOf("X") }
                val playField = PlayField(movePlayer = movePlayer)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    playField.renderField()
                }
            }
        }
    }
}

class PlayField( var sizeField: Int = 3, var movePlayer: String) {

    @Composable
    fun generateBox() {
        var statePlayer by remember { mutableStateOf("X") }
        var stateBoxList = remember {
            mutableStateListOf<Array<String>>()
        }

        LaunchedEffect(Unit) {
            stateBoxList.addAll(MutableList(sizeField){Array(sizeField) {""} })
        }

        val updateList = {
            val temp = stateBoxList.toList()
            stateBoxList.clear()
            stateBoxList.addAll(temp)
        }

        stateBoxList.forEachIndexed { indexRow, row ->
            Row(

            ) {
                row.forEachIndexed { indexBox, stateBox ->
                    Box(
                        Modifier
                            .width(80.dp)
                            .height(80.dp)
                            .padding(2.dp)
                            .border(2.dp, Color.LightGray)
                            .selectable(
                                onClick = {
                                    if (stateBox === "") {
                                        stateBoxList[indexRow][indexBox] = statePlayer
                                        updateList()
                                        statePlayer = if (statePlayer == "X") {
                                            "0"
                                        } else {
                                            "X"
                                        }
                                    }
                                },
                                selected = stateBox.toBoolean()
                            ),
                    ) {
                        when(stateBox) {
                            "X" -> Image(painter = painterResource(R.drawable.x), contentDescription = "x")
                            "0" -> Image(painter = painterResource(R.drawable.o), contentDescription = "0")
                            else -> null
                        }
                    }
                }
            }
        }
    }

    @Composable
     fun renderField(){
        Column(
            Modifier
                .fillMaxWidth()
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            generateBox()
        }
    }
}




