package com.pseudoankit.coachmark.model

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
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
        val coordinate: Offset,
    )
}