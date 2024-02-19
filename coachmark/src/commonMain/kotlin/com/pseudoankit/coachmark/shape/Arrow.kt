package com.pseudoankit.coachmark.shape

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pseudoankit.coachmark.util.CoachMarkDefaults
import com.pseudoankit.coachmark.util.buildPath
import com.pseudoankit.coachmark.util.toPx

/**
 * contract containing required fields/methods to draw arrow when drawing [Balloon] shape
 */
public sealed interface Arrow {

    /**
     * width of arrow
     */
    public val width: Dp

    /**
     * height of arrow
     */
    public val height: Dp

    /**
     * bias of the of arrow w.r.t view where it's applied
     * it basically denotes the position of arrow when placing it
     */
    public val bias: Float

    /**
     * padding to be applied from start/left of the view
     */
    public val startPadding: Dp

    /**
     * padding to be applied from end/right of the view
     */
    public val endPadding: Dp

    /**
     * padding to be applied from top of the view
     */
    public val topPadding: Dp

    /**
     * padding to be applied from bottom of the view
     */
    public val bottomPadding: Dp

    /**
     * @return path of the arrow to be added to the view
     */
    public fun draw(size: Size, density: Density): Path

    /**
     * draws arrow at top of view
     */
    public data class Top(
        override val width: Dp = CoachMarkDefaults.Balloon.Arrow.width,
        override val height: Dp = CoachMarkDefaults.Balloon.Arrow.height,
        override val bias: Float = CoachMarkDefaults.Balloon.Arrow.bias
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

    /**
     * draws arrow at bottom of view
     */
    public data class Bottom(
        override val width: Dp = CoachMarkDefaults.Balloon.Arrow.width,
        override val height: Dp = CoachMarkDefaults.Balloon.Arrow.height,
        override val bias: Float = CoachMarkDefaults.Balloon.Arrow.bias
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

    /**
     * draws arrow at start/left of view
     */
    public data class Start(
        override val width: Dp = CoachMarkDefaults.Balloon.Arrow.width,
        override val height: Dp = CoachMarkDefaults.Balloon.Arrow.height,
        override val bias: Float = CoachMarkDefaults.Balloon.Arrow.bias
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

    /**
     * draws arrow at right/end of view
     */
    public data class End(
        override val width: Dp = CoachMarkDefaults.Balloon.Arrow.width,
        override val height: Dp = CoachMarkDefaults.Balloon.Arrow.height,
        override val bias: Float = CoachMarkDefaults.Balloon.Arrow.bias
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