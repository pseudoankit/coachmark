package com.pseudoankit.coachmark.demo

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pseudoankit.coachmark.shape.Arrow
import com.pseudoankit.coachmark.shape.Balloon

@SuppressLint("ComposePreviewPublic")
@Preview
@Composable
public fun BalloonShapeDemo() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Balloon(arrow = Arrow.Start()) {
            Text(text = "Start")
        }
        Balloon(arrow = Arrow.End()) {
            Text(text = "End")
        }
        Balloon(arrow = Arrow.Top()) {
            Text(text = "Top")
        }
        Balloon(arrow = Arrow.Bottom()) {
            Text(text = "Bottom")
        }
    }
}