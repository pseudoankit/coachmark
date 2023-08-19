package com.pseudoankit.coachmark.shape

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.Dp
import com.pseudoankit.coachmark.util.CoachMarkDefaults

public interface Arrow {
    public val width: Dp
    public val height: Dp

    public data class Top(
        override val width: Dp = CoachMarkDefaults.Tooltip.Arrow.width,
        override val height: Dp = CoachMarkDefaults.Tooltip.Arrow.height,
        val alignment: Alignment.Horizontal = Alignment.CenterHorizontally
    ) : Arrow

    public data class Bottom(
        override val width: Dp = CoachMarkDefaults.Tooltip.Arrow.width,
        override val height: Dp = CoachMarkDefaults.Tooltip.Arrow.height,
        val alignment: Alignment.Horizontal = Alignment.CenterHorizontally
    ) : Arrow

    public data class Start(
        override val width: Dp = CoachMarkDefaults.Tooltip.Arrow.width,
        override val height: Dp = CoachMarkDefaults.Tooltip.Arrow.height,
        val alignment: Alignment.Vertical = Alignment.CenterVertically
    ) : Arrow

    public data class End(
        override val width: Dp = CoachMarkDefaults.Tooltip.Arrow.width,
        override val height: Dp = CoachMarkDefaults.Tooltip.Arrow.height,
        val alignment: Alignment.Vertical = Alignment.CenterVertically
    ) : Arrow
}