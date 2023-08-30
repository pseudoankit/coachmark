package com.pseudoankit.coachmark.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pseudoankit.coachmark.UnifyCoachmark
import com.pseudoankit.coachmark.model.ToolTipPlacement
import com.pseudoankit.coachmark.scope.CoachMarkScope
import com.pseudoankit.coachmark.shape.Arrow
import com.pseudoankit.coachmark.shape.Balloon
import com.pseudoankit.coachmark.util.CoachMarkKey


private enum class Keys { Start, End, Top, Bottom }

@Preview
@Composable
public fun UnifyCoachmarkDemo() {
    UnifyCoachmark(
        overlayContent = { OverlayContent(it) }
    ) {
        Column {
            Text(
                text = "Top",
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color.Yellow)
                    .enableCoachMark(
                        key = Keys.Start,
                        toolTipPlacement = ToolTipPlacement.Top
                    )
            )
        }
    }
}

@Composable
private fun CoachMarkScope.OverlayContent(key: CoachMarkKey) {
    when (key) {
        Keys.Top -> {
            Balloon(arrow = Arrow.Top()) {
                Text(text = "Top")
            }
        }
    }
}