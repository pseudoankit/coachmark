package com.pseudoankit.coachmark.internal

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.pseudoankit.coachmark.UnifyCoachMarkOverlayClickEvent

internal data class CoachMarkConfigInternal<T>(
    val itemConfig: ItemConfig,
    val overlayConfig: OverlayConfig,
    val coordinate: Offset,
    val key: T
) {
    data class OverlayConfig(
        val overlayColor: Color,
        val overlayClickEvent: UnifyCoachMarkOverlayClickEvent,
    )

    data class ItemConfig(
        val textColor: Color,
        val modifier: Modifier
    )
}