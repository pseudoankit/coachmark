package com.pseudoankit.coachmark.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import com.pseudoankit.coachmark.model.CoachMarkConfigInternal
import com.pseudoankit.coachmark.model.ToolTipPlacement
import com.pseudoankit.coachmark.util.toDp

@Composable
internal fun Tooltip(
    activeItem: CoachMarkConfigInternal,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .offset(
                x = activeItem.tooltip.offsetX(LocalDensity.current),
                y = activeItem.tooltip.offsetY(LocalDensity.current),
            )
            .then(activeItem.tooltip.modifier),
        contentAlignment = Alignment.Center,
        content = content
    )
}

private fun CoachMarkConfigInternal.Tooltip.offsetX(density: Density) = when (placement) {
    ToolTipPlacement.Start -> startX.toDp(density)
    ToolTipPlacement.End -> endX.toDp(density)
    ToolTipPlacement.Top -> startX.toDp(density)
    ToolTipPlacement.Bottom -> startX.toDp(density)
}

private fun CoachMarkConfigInternal.Tooltip.offsetY(density: Density) = when (placement) {
    ToolTipPlacement.Start -> startY.toDp(density)
    ToolTipPlacement.End -> startY.toDp(density) + height.div(2).toDp(density)
    ToolTipPlacement.Top -> startY.toDp(density)
    ToolTipPlacement.Bottom -> startY.toDp(density)
}