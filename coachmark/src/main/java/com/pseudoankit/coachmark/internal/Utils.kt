package com.pseudoankit.coachmark.internal

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Density
import com.pseudoankit.coachmark.UnifyCoachMarkConfig
import com.pseudoankit.coachmark.UnifyCoachMarkGlobalConfig

internal fun Modifier.clickable(
    showRipple: Boolean = true,
    onClick: () -> Unit
) = composed {
    clickable(
        indication = if (showRipple) LocalIndication.current else null,
        interactionSource = remember { MutableInteractionSource() },
        onClick = onClick
    )
}

internal fun Float.toDp(density: Density) = with(density) { toDp() }

internal fun <T> mapToInternalConfig(
    globalConfig: UnifyCoachMarkGlobalConfig<T>,
    config: UnifyCoachMarkConfig<T>,
    coordinate: Offset,
    key: T
) = CoachMarkConfigInternal(
    itemConfig = CoachMarkConfigInternal.ItemConfig(
        textColor = config.itemConfig.textColor ?: globalConfig.itemConfig.textColor,
        modifier = config.itemConfig.modifier ?: globalConfig.itemConfig.modifier,
        text = config.itemConfig.text
    ),
    overlayConfig = CoachMarkConfigInternal.OverlayConfig(
        overlayColor = config.overlayConfig.overlayColor ?: globalConfig.overlayConfig.overlayColor,
        onOverlayClicked = config.overlayConfig.onOverlayClicked
            ?: globalConfig.overlayConfig.onOverlayClicked,
    ),
    coordinate = coordinate,
    key = key
)