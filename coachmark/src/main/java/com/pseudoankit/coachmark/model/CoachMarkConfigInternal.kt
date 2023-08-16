package com.pseudoankit.coachmark.model

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.pseudoankit.coachmark.util.CoachMarkKey

internal data class CoachMarkConfigInternal(
    val itemConfig: ItemConfig,
    val overlayConfig: OverlayConfig,
    val coordinate: Offset,
    val key: CoachMarkKey
) {
    data class OverlayConfig(
        val overlayColor: Color,
        val onOverlayClicked: (key: CoachMarkKey) -> CoachMarkOverlayClickEvent
    )

    data class ItemConfig(
        val textColor: Color,
        val text: String,
        val modifier: Modifier,
    )
}