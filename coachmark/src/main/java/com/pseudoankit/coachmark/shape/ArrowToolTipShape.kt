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
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.pseudoankit.coachmark.util.CoachMarkDefaults
import com.pseudoankit.coachmark.util.toPx

@Preview
@Composable
public fun Preview() {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .wrapContentSize()
            .graphicsLayer {
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
    private val arrow: Arrow,
    private val cornerRadius: Dp = CoachMarkDefaults.Tooltip.cornerRadius,
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val radius = cornerRadius.toPx(density)
        val arrowWidth = arrow.width.toPx(density)
        val arrowHeight = arrow.height.toPx(density)

        return Outline.Generic(Path().apply {

            addRoundRect(
                RoundRect(
                    left = 0f,
                    right = size.width,
                    top = arrowHeight,
                    bottom = (size.height + arrowHeight),
                    cornerRadius = CornerRadius(radius)
                )
            )

            addPath(Path().apply {
                moveTo(size.width.div(2) - arrowWidth.div(2), arrowHeight)
                lineTo(size.width.div(2), 0f)
                lineTo(size.width.div(2) + arrowWidth.div(2), arrowHeight)
                close()
            })

            close()
        })
    }
}