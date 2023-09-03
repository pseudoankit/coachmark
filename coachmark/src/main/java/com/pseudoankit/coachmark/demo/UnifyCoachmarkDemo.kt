package com.pseudoankit.coachmark.demo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pseudoankit.coachmark.UnifyCoachmark
import com.pseudoankit.coachmark.model.HighlightedViewConfig
import com.pseudoankit.coachmark.model.OverlayClickEvent
import com.pseudoankit.coachmark.model.ToolTipPlacement
import com.pseudoankit.coachmark.overlay.DimOverlayEffect
import com.pseudoankit.coachmark.shape.Arrow
import com.pseudoankit.coachmark.shape.Balloon
import com.pseudoankit.coachmark.util.CoachMarkKey

public enum class Keys { Text1, Text2 }

@Preview
@Composable
public fun UnifyCoachmarkDemo() {
    UnifyCoachmark(
        tooltip = { Tooltip(it) },
        overlayEffect = DimOverlayEffect(Color.Black.copy(alpha = .5f)),
        onOverlayClicked = { OverlayClickEvent.GoNext }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Will show tooltip 1",
                modifier = Modifier
                    .enableCoachMark(
                        key = Keys.Text1,
                        toolTipPlacement = ToolTipPlacement.End,
                        highlightedViewConfig = HighlightedViewConfig(
                            shape = HighlightedViewConfig.Shape.Rect(12.dp),
                            padding = PaddingValues(8.dp)
                        )
                    )
                    .padding(16.dp),
                color = Color.Black
            )
            Text(
                text = "Will show tooltip 2",
                modifier = Modifier
                    .enableCoachMark(
                        key = Keys.Text2,
                        toolTipPlacement = ToolTipPlacement.End,
                        highlightedViewConfig = HighlightedViewConfig(
                            shape = HighlightedViewConfig.Shape.Rect(12.dp),
                            padding = PaddingValues(8.dp)
                        )
                    )
                    .padding(16.dp),
                color = Color.Black
            )
            Button(
                onClick = {
                    show(Keys.Text1)
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Highlight1")
            }
            Button(
                onClick = {
                    show(Keys.Text1, Keys.Text2)
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Highlight 1 & 2")
            }
        }
    }
}

@Composable
private fun Tooltip(key: CoachMarkKey) {
    when (key) {
        Keys.Text1 -> {
            Balloon(arrow = Arrow.Start()) {
                Text(text = "Highlighting Text1", color = Color.White)
            }
        }

        Keys.Text2 -> {
            Balloon(arrow = Arrow.Start()) {
                Text(text = "Highlighting Text2", color = Color.White)
            }
        }
    }
}