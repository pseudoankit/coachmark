package com.pseudoankit.coachmark.internal.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import com.pseudoankit.coachmark.internal.CoachMarkConfigInternal
import com.pseudoankit.coachmark.internal.util.rememberMutableStateOf
import com.pseudoankit.coachmark.internal.util.toDp
import com.pseudoankit.coachmark.model.ToolTipPlacement

@Composable
internal fun Tooltip(
    activeItem: CoachMarkConfigInternal,
    content: @Composable BoxScope.() -> Unit
) {
    var toolTipSize by rememberMutableStateOf(value = IntSize(0, 0))

    Box(
        modifier = Modifier
            .onGloballyPositioned {
                toolTipSize = it.size
            }
            .offset(
                x = activeItem.offsetX(LocalDensity.current, toolTipSize),
                y = activeItem.offsetY(LocalDensity.current, toolTipSize),
            )
            .then(activeItem.tooltip.modifier),
        contentAlignment = Alignment.Center,
        content = content
    )
}

private fun CoachMarkConfigInternal.offsetX(
    density: Density, toolTipSize: IntSize
) = when (tooltip.placement) {
    ToolTipPlacement.Start -> focusedView.startX.toDp(density)
    ToolTipPlacement.End -> focusedView.endX.toDp(density)
    ToolTipPlacement.Top -> focusedView.startX.toDp(density)
    ToolTipPlacement.Bottom -> focusedView.startX.toDp(density)
}

private fun CoachMarkConfigInternal.offsetY(
    density: Density, toolTipSize: IntSize
) = when (tooltip.placement) {
    ToolTipPlacement.Start, ToolTipPlacement.End -> {
        val viewCenter = (focusedView.startY + focusedView.endY).div(2)
        val toolTipCenter = toolTipSize.height.div(2)
        (viewCenter - toolTipCenter).toDp(density)
    }

    ToolTipPlacement.Top -> (focusedView.startY - toolTipSize.height).toDp(density)
    ToolTipPlacement.Bottom -> focusedView.endY.toDp(density)
}