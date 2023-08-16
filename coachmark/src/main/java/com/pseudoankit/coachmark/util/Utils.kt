package com.pseudoankit.coachmark.util

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import com.pseudoankit.coachmark.model.CoachMarkConfigInternal
import com.pseudoankit.coachmark.model.UnifyCoachMarkConfig
import com.pseudoankit.coachmark.model.UnifyCoachMarkGlobalConfig

internal fun Float.toDp(density: Density) = with(density) { toDp() }

internal fun Dp.toPx(density: Density) = with(density) { toPx() }

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

internal fun <KEY> mapToInternalConfig(
    globalConfig: UnifyCoachMarkGlobalConfig<KEY>,
    config: UnifyCoachMarkConfig<KEY>,
    coordinate: Offset,
    key: KEY
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