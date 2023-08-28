package com.pseudoankit.coachmark_demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.pseudoankit.coachmark.shape.BalloonPreview
import com.pseudoankit.coachmark_demo.ui.theme.CoachmarkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoachmarkTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier.fillMaxSize(),
                ) {
//                    UnifyCoachmarkDemo()

                    BalloonPreview()
                }
            }
        }
    }
}
