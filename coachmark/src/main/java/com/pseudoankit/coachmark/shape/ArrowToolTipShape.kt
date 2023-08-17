package com.pseudoankit.coachmark.shape

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import com.pseudoankit.coachmark.util.toPx

public class ArrowToolTipShape(
    private val direction: Direction,
    private val cornerRadius: Dp
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val radius = cornerRadius.toPx(density)

        val path = Path().apply {
            moveTo(0f, 0f)
            addRoundRect(
                RoundRect(
                    rect = Rect(Offset(0f, 0f), size),
                    cornerRadius = CornerRadius(radius)
                )
            )
        }
        return Outline.Generic(path)
    }

    public enum class Direction {
        Top, Bottom, Left, Right
    }
}