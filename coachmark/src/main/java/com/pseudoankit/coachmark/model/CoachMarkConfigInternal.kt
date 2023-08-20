package com.pseudoankit.coachmark.model

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.pseudoankit.coachmark.overlay.OverlayClickEvent
import com.pseudoankit.coachmark.util.CoachMarkKey

internal data class CoachMarkConfigInternal(
    val tooltip: Tooltip,
    val overlay: Overlay,
    val key: CoachMarkKey
) {
    data class Overlay(
        val color: Color,
        val onClick: (key: CoachMarkKey) -> OverlayClickEvent
    )

    data class Tooltip(
        val textColor: Color,
        val text: String,
        val modifier: Modifier,
        val position: Position,
    )

    data class Position(
        val startX: Float,
        val startY: Float,
        val width: Int,
        val height: Int
    ) {
        val endX: Float = startX + width
        val endY: Float = startY + height
    }
}
