package com.pseudoankit.coachmark.shape

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pseudoankit.coachmark.CoachMarkDefaults
import com.pseudoankit.coachmark.internal.util.buildPath
import com.pseudoankit.coachmark.internal.util.toPx

public sealed interface Arrow {
    public val width: Dp
    public val height: Dp

    public val startPadding: Dp
    public val endPadding: Dp
    public val topPadding: Dp
    public val bottomPadding: Dp

    public fun draw(size: Size, density: Density): Path


    public data class Top(
        override val width: Dp = CoachMarkDefaults.Balloon.Arrow.width,
        override val height: Dp = CoachMarkDefaults.Balloon.Arrow.height,
        val bias: Float = CoachMarkDefaults.Balloon.Arrow.bias
    ) : Arrow {

        override val topPadding: Dp = height
        override val startPadding: Dp = 0.dp
        override val endPadding: Dp = 0.dp
        override val bottomPadding: Dp = 0.dp

        override fun draw(size: Size, density: Density): Path = buildPath {
            val widthPx = width.toPx(density)
            val heightPx = height.toPx(density)

            moveTo(size.width.times(bias) - widthPx.div(2), heightPx)
            lineTo(size.width.times(bias), 0f)
            lineTo(size.width.times(bias) + widthPx.div(2), heightPx)
        }
    }

    public data class Bottom(
        override val width: Dp = CoachMarkDefaults.Balloon.Arrow.width,
        override val height: Dp = CoachMarkDefaults.Balloon.Arrow.height,
        val bias: Float = CoachMarkDefaults.Balloon.Arrow.bias
    ) : Arrow {

        override val bottomPadding: Dp = height
        override val topPadding: Dp = 0.dp
        override val startPadding: Dp = 0.dp
        override val endPadding: Dp = 0.dp

        override fun draw(size: Size, density: Density): Path = buildPath {
            val widthPx = width.toPx(density)
            val heightPx = height.toPx(density)

            moveTo(size.width.times(bias) - widthPx.div(2), size.height - heightPx)
            lineTo(size.width.times(bias), size.height)
            lineTo(size.width.times(bias) + widthPx.div(2), size.height - heightPx)
        }
    }

    public data class Start(
        override val width: Dp = CoachMarkDefaults.Balloon.Arrow.width,
        override val height: Dp = CoachMarkDefaults.Balloon.Arrow.height,
        val bias: Float = CoachMarkDefaults.Balloon.Arrow.bias
    ) : Arrow {

        override val startPadding: Dp = width
        override val topPadding: Dp = 0.dp
        override val endPadding: Dp = 0.dp
        override val bottomPadding: Dp = 0.dp

        override fun draw(size: Size, density: Density): Path = buildPath {
            val widthPx = width.toPx(density)
            val heightPx = height.toPx(density)

            moveTo(widthPx, size.height.times(bias) - heightPx.div(2))
            lineTo(0f, size.height.times(bias))
            lineTo(widthPx, size.height.times(bias) + heightPx.div(2))
        }
    }

    public data class End(
        override val width: Dp = CoachMarkDefaults.Balloon.Arrow.width,
        override val height: Dp = CoachMarkDefaults.Balloon.Arrow.height,
        val bias: Float = CoachMarkDefaults.Balloon.Arrow.bias
    ) : Arrow {

        override val endPadding: Dp = width
        override val topPadding: Dp = 0.dp
        override val startPadding: Dp = 0.dp
        override val bottomPadding: Dp = 0.dp

        override fun draw(size: Size, density: Density): Path = buildPath {
            val widthPx = width.toPx(density)
            val heightPx = height.toPx(density)

            moveTo(size.width - widthPx, size.height.times(bias) - heightPx.div(2))
            lineTo(size.width, size.height.times(bias))
            lineTo(size.width - widthPx, size.height.times(bias) + heightPx.div(2))
        }
    }
}