package com.pseudoankit.coachmark.demo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
import com.pseudoankit.coachmark.LocalCoachMarkScope
import com.pseudoankit.coachmark.UnifyCoachmark
import com.pseudoankit.coachmark.model.HighlightedViewConfig
import com.pseudoankit.coachmark.model.OverlayClickEvent
import com.pseudoankit.coachmark.model.ToolTipPlacement
import com.pseudoankit.coachmark.overlay.DimOverlayEffect
import com.pseudoankit.coachmark.shape.Arrow
import com.pseudoankit.coachmark.shape.Balloon
import com.pseudoankit.coachmark.util.CoachMarkKey

public enum class Keys { Text1, Text2, TextStart, TextBottom, TextTop }

@Composable
private fun ColumnScope.PlotTextsAndUseLocalCoachMarkScope() {

    CoachMarkTargetText("Will show tooltip 1", Alignment.Start, Keys.Text1, ToolTipPlacement.End)

    CoachMarkTargetText("Will show tooltip 2", Alignment.Start, Keys.Text2, ToolTipPlacement.End)

    CoachMarkTargetText(
        "Will show tooltip to left",
        Alignment.End,
        Keys.TextStart,
        ToolTipPlacement.Start
    )

    CoachMarkTargetText(
        "Will show tooltip below",
        Alignment.CenterHorizontally,
        Keys.TextBottom,
        ToolTipPlacement.Bottom
    )

    CoachMarkTargetText(
        "Will show tooltip above",
        Alignment.CenterHorizontally,
        Keys.TextTop,
        ToolTipPlacement.Top
    )

}

@Composable
private fun ColumnScope.CoachMarkTargetText(
    text: String,
    alignment: Alignment.Horizontal,
    key: Keys,
    placement: ToolTipPlacement,
) {
    val coachMarkScope = LocalCoachMarkScope.current

    coachMarkScope?.apply {
        Text(
            text = text,
            modifier = Modifier
                .align(alignment)
                .enableCoachMark(
                    key = key,
                    toolTipPlacement = placement,
                    highlightedViewConfig = HighlightedViewConfig(
                        shape = HighlightedViewConfig.Shape.Rect(12.dp),
                        padding = PaddingValues(8.dp)
                    )
                )
                .padding(16.dp),
            color = Color.Black
        )
    }
}

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
            PlotTextsAndUseLocalCoachMarkScope()
            Button(
                onClick = {
                    show(Keys.Text1)
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Highlight 1")
            }
            Button(
                onClick = {
                    show(*Keys.values())
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Highlight All")
            }
            Button(onClick = { show(Keys.TextBottom, Keys.TextTop) }) {
                Text(text = "Highlight Some")
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

        Keys.TextStart -> {
            Balloon(arrow = Arrow.End()) {
                Text(text = "A tooltip to the left", color = Color.White)
            }
        }

        Keys.TextBottom -> {
            Balloon(arrow = Arrow.Top()) {
                Text(text = "A tooltip below", color = Color.White)
            }
        }

        Keys.TextTop -> {
            Balloon(arrow = Arrow.Bottom()) {
                Text(text = "A tooltip above", color = Color.White)
            }
        }
    }
}