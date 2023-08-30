package com.pseudoankit.coachmark.model

import com.pseudoankit.coachmark.util.CoachMarkKey

internal data class CoachMarkConfig(
    val layout: Layout,
    val toolTipPlacement: ToolTipPlacement,
    val key: CoachMarkKey
) {

    data class Layout(
        val width: Int,
        val height: Int,
        val startX: Float,
        val startY: Float,
    ) {
        val endX: Float = startX + width
        val endY: Float = startY + height
    }
}
