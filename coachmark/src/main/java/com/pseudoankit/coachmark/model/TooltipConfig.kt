package com.pseudoankit.coachmark.model

import androidx.compose.runtime.Stable
import com.pseudoankit.coachmark.util.CoachMarkKey

@Stable
public data class TooltipConfig(
    val layout: Layout,
    val toolTipPlacement: ToolTipPlacement,
    val key: CoachMarkKey,
    val highlightedViewShape: HighlightedViewConfig.Shape,
    val animationState: AnimationState = AnimationState()
) {

    @Stable
    public data class Layout(
        val width: Int,
        val height: Int,
        val startX: Float,
        val startY: Float,
    ) {
        val endX: Float = startX + width
        val endY: Float = startY + height
    }

    @Stable
    public data class AnimationState(
        val initialAlpha: Float = 0f,
        val targetAlpha: Float = 0f
    )
}
