package com.pseudoankit.coachmark.internal

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.pseudoankit.coachmark.CoachMarkKey
import com.pseudoankit.coachmark.model.OverlayClickEvent
import com.pseudoankit.coachmark.model.ToolTipPlacement

internal data class CoachMarkConfigInternal(
    val tooltip: Tooltip,
    val overlay: Overlay,
    val key: CoachMarkKey,
    val focusedView: FocusedView
) {
    data class Overlay(
        val color: Color,
        val onClick: (key: CoachMarkKey) -> OverlayClickEvent
    )

    data class FocusedView(
        val width: Int,
        val height: Int,
        val startX: Float,
        val startY: Float,
    ) {
        val endX: Float = startX + width
        val endY: Float = startY + height
    }

    data class Tooltip(
        val textColor: Color,
        val text: String,
        val modifier: Modifier,
        val placement: ToolTipPlacement,
    )
}
