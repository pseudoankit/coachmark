package com.pseudoankit.coachmark.internal.util

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.positionInRoot
import com.pseudoankit.coachmark.internal.CoachMarkConfigInternal
import com.pseudoankit.coachmark.model.CoachMarkConfig
import com.pseudoankit.coachmark.model.CoachMarkGlobalConfig

@SuppressLint("ModifierFactoryExtensionFunction")
internal fun createToolTipModifier(
    bgColor: Color,
    shape: Shape,
    padding: PaddingValues,
) = Modifier
    .graphicsLayer {
        shadowElevation = 1f
        this.shape = shape
        clip = true
    }
    .background(bgColor)
    .toolTipContentPadding(shape)
    .padding(padding)

internal fun mapToInternalConfig(
    globalConfig: CoachMarkGlobalConfig,
    config: CoachMarkConfig,
    layoutCoordinates: LayoutCoordinates,
) = CoachMarkConfigInternal(
    tooltip = CoachMarkConfigInternal.Tooltip(
        textColor = config.tooltip.textColor ?: globalConfig.tooltip.textColor,
        modifier = config.tooltip.modifier ?: globalConfig.tooltip.modifier,
        text = config.tooltip.text,
        startX = layoutCoordinates.positionInRoot().x,
        startY = layoutCoordinates.positionInRoot().y,
        placement = config.tooltip.placement,
        width = layoutCoordinates.size.width,
        height = layoutCoordinates.size.height
    ),
    overlay = CoachMarkConfigInternal.Overlay(
        color = config.overlay.color ?: globalConfig.overlay.color,
        onClick = config.overlay.onClick ?: globalConfig.overlay.onClick,
    ),
    key = config.key
)