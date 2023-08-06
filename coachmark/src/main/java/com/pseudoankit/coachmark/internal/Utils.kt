package com.pseudoankit.coachmark.internal

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Density
import com.pseudoankit.coachmark.UnifyCoachMarkConfig
import com.pseudoankit.coachmark.UnifyCoachMarkGlobalConfig

internal fun Float.toDp(density: Density) = with(density) { toDp() }

internal fun <T> mapToInternalConfig(
    globalConfig: UnifyCoachMarkGlobalConfig,
    config: UnifyCoachMarkConfig,
    coordinate: Offset,
    key: T
) = CoachMarkConfigInternal(
    itemConfig = CoachMarkConfigInternal.ItemConfig(
        textColor = config.itemConfig.textColor ?: globalConfig.itemConfig.textColor,
        modifier = config.itemConfig.modifier ?: globalConfig.itemConfig.modifier,
    ),
    overlayConfig = CoachMarkConfigInternal.OverlayConfig(
        overlayColor = config.overlayConfig.overlayColor ?: globalConfig.overlayConfig.overlayColor,
        overlayClickEvent = config.overlayConfig.overlayClickEvent
            ?: globalConfig.overlayConfig.overlayClickEvent,
    ),
    coordinate = coordinate,
    key = key
)