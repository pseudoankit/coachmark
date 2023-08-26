package com.pseudoankit.coachmark.shape

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.pseudoankit.coachmark.CoachMarkDefaults
import com.pseudoankit.coachmark.internal.util.bottomPadding
import com.pseudoankit.coachmark.internal.util.buildPath
import com.pseudoankit.coachmark.internal.util.leftPadding
import com.pseudoankit.coachmark.internal.util.rightPadding
import com.pseudoankit.coachmark.internal.util.toPx
import com.pseudoankit.coachmark.internal.util.topPadding

@Preview
@Composable
public fun Preview() {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .wrapContentSize()
            .graphicsLayer {
                shadowElevation = 1f
                shape = ArrowToolTipShape(Arrow.Top())
                clip = true
            }
            .background(Color.Yellow)
            .padding(top = 22.dp)
            .padding(8.dp)
    ) {
        Text(text = "Hey I am ankit kumar in")
    }
}

public class ArrowToolTipShape(
    internal val arrow: Arrow,
    private val cornerRadius: Dp = CoachMarkDefaults.Tooltip.cornerRadius,
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val radius = cornerRadius.toPx(density)

        return Outline.Generic(buildPath {

            addRoundRect(
                RoundRect(
                    left = arrow.leftPadding().toPx(density),
                    right = size.width - arrow.rightPadding().toPx(density),
                    top = arrow.topPadding().toPx(density),
                    bottom = size.height - arrow.bottomPadding().toPx(density),
                    cornerRadius = CornerRadius(radius)
                )
            )

            addPath(arrow.draw(size, density))

        })
    }
}