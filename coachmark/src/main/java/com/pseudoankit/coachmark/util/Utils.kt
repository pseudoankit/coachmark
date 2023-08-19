package com.pseudoankit.coachmark.util

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import com.pseudoankit.coachmark.model.CoachMarkConfig
import com.pseudoankit.coachmark.model.CoachMarkConfigInternal
import com.pseudoankit.coachmark.model.CoachMarkGlobalConfig

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

internal fun mapToInternalConfig(
    globalConfig: CoachMarkGlobalConfig,
    config: CoachMarkConfig,
    layoutCoordinates: LayoutCoordinates,
) = CoachMarkConfigInternal(
    tooltip = CoachMarkConfigInternal.Tooltip(
        textColor = config.tooltip.textColor ?: globalConfig.tooltip.textColor,
        modifier = config.tooltip.modifier ?: globalConfig.tooltip.modifier,
        text = config.tooltip.text,
        positionConifg = CoachMarkConfigInternal.PositionConifg(
            startX = layoutCoordinates.positionInRoot().x,
            startY = layoutCoordinates.positionInRoot().y,
            width = layoutCoordinates.size.width,
            height = layoutCoordinates.size.height
        )
    ),
    overlay = CoachMarkConfigInternal.Overlay(
        color = config.overlay.color ?: globalConfig.overlay.color,
        onClick = config.overlay.onClick ?: globalConfig.overlay.onClick,
    ),
    key = config.key
)