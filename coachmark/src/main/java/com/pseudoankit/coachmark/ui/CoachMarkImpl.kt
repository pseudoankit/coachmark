package com.pseudoankit.coachmark.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import com.pseudoankit.coachmark.model.CoachMarkConfig
import com.pseudoankit.coachmark.model.ToolTipPlacement
import com.pseudoankit.coachmark.overlay.UnifyOverlayBackground
import com.pseudoankit.coachmark.scope.CoachMarkScope
import com.pseudoankit.coachmark.scope.CoachMarkScopeImpl
import com.pseudoankit.coachmark.util.CoachMarkKey
import com.pseudoankit.coachmark.util.clickable
import com.pseudoankit.coachmark.util.rememberMutableStateOf
import com.pseudoankit.coachmark.util.toDp

@Composable
internal fun CoachMarkImpl(
    overlay: UnifyOverlayBackground,
    scope: CoachMarkScopeImpl,
    overlayContent: @Composable CoachMarkScope.(CoachMarkKey) -> Unit,
    content: @Composable (CoachMarkScope.() -> Unit),
) {
    val activeItem = scope.currentVisibleTooltip

    val density = LocalDensity.current
    var toolTipSize by rememberMutableStateOf(value = IntSize(0, 0))

    Box(modifier = Modifier.fillMaxSize()) {
        content(scope)

        if (activeItem != null) {
            overlay.Background {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable(showRipple = false, onClick = scope::onOverlayClicked)
                ) {
                    Box(
                        modifier = Modifier
                            .onGloballyPositioned {
                                toolTipSize = it.size
                            }
                            .offset(
                                x = activeItem.offsetX(density, toolTipSize),
                                y = activeItem.offsetY(density, toolTipSize),
                            )
                    ) {
                        scope.overlayContent(activeItem.key)
                    }
                }
            }
        }
    }
}

private fun CoachMarkConfig.offsetX(
    density: Density, toolTipSize: IntSize
) = when (toolTipPlacement) {
    ToolTipPlacement.Start -> layout.startX.toDp(density)
    ToolTipPlacement.End -> layout.endX.toDp(density)
    ToolTipPlacement.Top -> layout.startX.toDp(density)
    ToolTipPlacement.Bottom -> layout.startX.toDp(density)
}

private fun CoachMarkConfig.offsetY(
    density: Density, toolTipSize: IntSize
) = when (toolTipPlacement) {
    ToolTipPlacement.Start, ToolTipPlacement.End -> {
        val viewCenter = (layout.startY + layout.endY).div(2)
        val toolTipCenter = toolTipSize.height.div(2)
        (viewCenter - toolTipCenter).toDp(density)
    }

    ToolTipPlacement.Top -> (layout.startY - toolTipSize.height).toDp(density)
    ToolTipPlacement.Bottom -> layout.endY.toDp(density)
}