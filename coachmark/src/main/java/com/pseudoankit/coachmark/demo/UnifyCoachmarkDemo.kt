package com.pseudoankit.coachmark.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.pseudoankit.coachmark.util.clickable

@Preview
@Composable
public fun UnifyCoachmarkDemo() {
    UnifyCoachmark(
        tooltip = { Tooltip(it) },
        overlayEffect = DimOverlayEffect(Color.Black.copy(alpha = .5f)),
        onOverlayClicked = { OverlayClickEvent.GoNext }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            ToolTipPlacement.values().forEach {
                Text(
                    text = it.name,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .clickable {
                            show(*ToolTipPlacement.values())
                        }
                        .background(Color.Red.copy(alpha = .5f))
                        .enableCoachMark(
                            key = it,
                            toolTipPlacement = it,
                            highlightedViewConfig = HighlightedViewConfig(
                                shape = HighlightedViewConfig.Shape.Rect(12.dp),
                                padding = PaddingValues(8.dp)
                            )
                        )
                        .padding(16.dp),
                    color = Color.White
                )
            }
        }
    }
}

@Composable
private fun Tooltip(key: CoachMarkKey) {
    when (key) {
        ToolTipPlacement.Start -> {
            Balloon(arrow = Arrow.End()) {
                Text(text = "Start", color = Color.White)
            }
        }

        ToolTipPlacement.End -> {
            Balloon(arrow = Arrow.Start()) {
                Text(text = "End", color = Color.White)
            }
        }

        ToolTipPlacement.Top -> {
            Balloon(arrow = Arrow.Bottom()) {
                Text(text = "Top", color = Color.White)
            }
        }

        ToolTipPlacement.Bottom -> {
            Balloon(arrow = Arrow.Top()) {
                Text(text = "Bottom", color = Color.White)
            }
        }
    }
}