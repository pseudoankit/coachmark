package com.pseudoankit.coachmark.internal

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.pseudoankit.coachmark.UnifyCoachMarkOverlayClickEvent

internal data class CoachMarkConfigInternal<T>(
    val itemConfig: ItemConfig,
    val overlayConfig: OverlayConfig<T>,
    val coordinate: Offset,
    val key: T
) {
    data class OverlayConfig<T>(
        val overlayColor: Color,
        val onOverlayClicked: (key: T) -> UnifyCoachMarkOverlayClickEvent
    )

    data class ItemConfig(
        val textColor: Color,
        val text: String,
        val modifier: Modifier,
    )
}