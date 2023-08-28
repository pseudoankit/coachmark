package com.pseudoankit.coachmark.shape

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pseudoankit.coachmark.CoachMarkDefaults
import com.pseudoankit.coachmark.internal.util.toPx

@Composable
public fun Balloon(
    arrow: Arrow,
    modifier: Modifier = Modifier.padding(CoachMarkDefaults.Balloon.padding),
    cornerRadius: Dp = CoachMarkDefaults.Balloon.cornerRadius,
    shadowElevation: Dp = CoachMarkDefaults.Balloon.shadowElevation,
    bgColor: Color = CoachMarkDefaults.Balloon.bgColor,
    content: @Composable BoxScope.() -> Unit
) {
    val density = LocalDensity.current

    Box(
        modifier = Modifier
            .graphicsLayer {
                shape = balloonShape(arrow, density, cornerRadius)
                clip = true
                this.shadowElevation = shadowElevation.toPx(density)
            }
            .background(bgColor)
            .padding(
                start = arrow.startPadding,
                end = arrow.endPadding,
                top = arrow.topPadding,
                bottom = arrow.bottomPadding
            )
            .then(modifier),
        content = content
    )
    PaddingValues().calculateBottomPadding()
}

private fun balloonShape(
    arrow: Arrow,
    density: Density,
    radius: Dp
) = GenericShape { size, _ ->

    addRoundRect(
        RoundRect(
            left = arrow.startPadding.toPx(density),
            right = size.width - arrow.endPadding.toPx(density),
            top = arrow.topPadding.toPx(density),
            bottom = size.height - arrow.bottomPadding
                .toPx(density),
            cornerRadius = CornerRadius(radius.toPx(density))
        )
    )

    addPath(arrow.draw(size, density))
}

@Preview
@Composable
public fun BalloonPreview() {
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
