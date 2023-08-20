package com.pseudoankit.coachmark.shape

import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import com.pseudoankit.coachmark.util.CoachMarkDefaults
import com.pseudoankit.coachmark.util.buildPath
import com.pseudoankit.coachmark.util.toPx

public sealed interface Arrow {
    public val width: Dp
    public val height: Dp
    public fun draw(size: Size, density: Density): Path

    public data class Top(
        override val width: Dp = CoachMarkDefaults.Tooltip.Arrow.width,
        override val height: Dp = CoachMarkDefaults.Tooltip.Arrow.height,
        val alignment: Alignment.Horizontal = Alignment.CenterHorizontally
    ) : Arrow {
        override fun draw(size: Size, density: Density): Path = buildPath {
            val widthPx = width.toPx(density)
            val heightPx = height.toPx(density)

            moveTo(size.width.div(2) - widthPx.div(2), heightPx)
            lineTo(size.width.div(2), 0f)
            lineTo(size.width.div(2) + widthPx.div(2), heightPx)
        }
    }

    public data class Bottom(
        override val width: Dp = CoachMarkDefaults.Tooltip.Arrow.width,
        override val height: Dp = CoachMarkDefaults.Tooltip.Arrow.height,
        val alignment: Alignment.Horizontal = Alignment.CenterHorizontally
    ) : Arrow {
        override fun draw(size: Size, density: Density): Path = buildPath {
            val widthPx = width.toPx(density)
            val heightPx = height.toPx(density)

            moveTo(size.width.div(2) - widthPx.div(2), size.height - heightPx)
            lineTo(size.width.div(2), size.height)
            lineTo(size.width.div(2) + widthPx.div(2), size.height - heightPx)
        }
    }

    public data class Start(
        override val width: Dp = CoachMarkDefaults.Tooltip.Arrow.width,
        override val height: Dp = CoachMarkDefaults.Tooltip.Arrow.height,
        val alignment: Alignment.Vertical = Alignment.CenterVertically
    ) : Arrow {
        override fun draw(size: Size, density: Density): Path = buildPath {
            val widthPx = width.toPx(density)
            val heightPx = height.toPx(density)

            moveTo(widthPx, size.height.div(2) - heightPx.div(2))
            lineTo(0f, size.height.div(2))
            lineTo(widthPx, size.height.div(2) + heightPx.div(2))
        }
    }

    public data class End(
        override val width: Dp = CoachMarkDefaults.Tooltip.Arrow.width,
        override val height: Dp = CoachMarkDefaults.Tooltip.Arrow.height,
        val alignment: Alignment.Vertical = Alignment.CenterVertically
    ) : Arrow {
        override fun draw(size: Size, density: Density): Path = buildPath {
            val widthPx = width.toPx(density)
            val heightPx = height.toPx(density)

            moveTo(size.width - widthPx, size.height.div(2) - heightPx.div(2))
            lineTo(size.width, size.height.div(2))
            lineTo(size.width - widthPx, size.height.div(2) + heightPx.div(2))
        }
    }

    public data class Auto(
        override val width: Dp = CoachMarkDefaults.Tooltip.Arrow.width,
        override val height: Dp = CoachMarkDefaults.Tooltip.Arrow.height
    ) : Arrow {
        override fun draw(size: Size, density: Density): Path = buildPath {
            TODO("WIP")
            val widthPx = width.toPx(density)
            val heightPx = height.toPx(density)
        }
    }
}