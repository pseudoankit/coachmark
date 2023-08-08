package com.pseudoankit.coachmark.internal

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.pseudoankit.coachmark.model.UnifyCoachMarkOverlayClickEvent

internal data class CoachMarkConfigInternal<KEY>(
    val itemConfig: ItemConfig,
    val overlayConfig: OverlayConfig<KEY>,
    val coordinate: Offset,
    val key: KEY
) {
    data class OverlayConfig<KEY>(
        val overlayColor: Color,
        val onOverlayClicked: (key: KEY) -> UnifyCoachMarkOverlayClickEvent
    )

    data class ItemConfig(
        val textColor: Color,
        val text: String,
        val modifier: Modifier,
    )
}