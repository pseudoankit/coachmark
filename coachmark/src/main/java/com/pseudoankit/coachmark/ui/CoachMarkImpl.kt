package com.pseudoankit.coachmark.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import com.pseudoankit.coachmark.model.ToolTipPlacement
import com.pseudoankit.coachmark.model.TooltipConfig
import com.pseudoankit.coachmark.overlay.UnifyOverlayEffect
import com.pseudoankit.coachmark.scope.CoachMarkScope
import com.pseudoankit.coachmark.scope.CoachMarkScopeImpl
import com.pseudoankit.coachmark.util.CoachMarkKey
import com.pseudoankit.coachmark.util.INVISIBLE_ALPHA
import com.pseudoankit.coachmark.util.VISIBLE_ALPHA
import com.pseudoankit.coachmark.util.clickable
import com.pseudoankit.coachmark.util.rememberMutableStateOf
import com.pseudoankit.coachmark.util.rememberTooltipHolder
import com.pseudoankit.coachmark.util.toDp

@Composable
internal fun CoachMarkImpl(
    overlayEffect: UnifyOverlayEffect,
    scope: CoachMarkScopeImpl,
    overlayContent: @Composable CoachMarkScope.(CoachMarkKey) -> Unit,
    content: @Composable (CoachMarkScope.() -> Unit),
) = with(overlayEffect) {
    val currentVisibleTooltip by rememberTooltipHolder(
        item = scope.currentVisibleTooltip,
        animationSpec = overlayEffect.tooltipAnimationSpec()
    )

    val density = LocalDensity.current
    var toolTipSize by rememberMutableStateOf(value = IntSize(0, 0))

    Box(modifier = Modifier.fillMaxSize()) {
        content(scope)

        scope.Overlay(
            modifier = Modifier
                .fillMaxSize()
                .run {
                    if (currentVisibleTooltip.alpha > INVISIBLE_ALPHA) {
                        clickable(showRipple = false, onClick = scope::onOverlayClicked)
                    } else this
                }
                .alpha(
                    animateFloatAsState(
                        targetValue = if (currentVisibleTooltip.isVisible) VISIBLE_ALPHA else INVISIBLE_ALPHA,
                        animationSpec = overlayEffect.overlayAnimationSpec()
                    ).value
                )
        ) {
            currentVisibleTooltip.item?.let { activeItem ->
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

private fun TooltipConfig.offsetX(
    density: Density, toolTipSize: IntSize
) = when (toolTipPlacement) {
    ToolTipPlacement.Start -> layout.startX - toolTipSize.width
    ToolTipPlacement.End -> layout.endX
    ToolTipPlacement.Top, ToolTipPlacement.Bottom -> {
        val viewCenter = (layout.startX + layout.endX).div(2)
        val toolTipCenter = (toolTipSize.width).div(2)
        viewCenter - toolTipCenter
    }
}.toDp(density)

private fun TooltipConfig.offsetY(
    density: Density, toolTipSize: IntSize
) = when (toolTipPlacement) {
    ToolTipPlacement.Start, ToolTipPlacement.End -> {
        val viewCenter = (layout.startY + layout.endY).div(2)
        val toolTipCenter = toolTipSize.height.div(2)
        viewCenter - toolTipCenter
    }

    ToolTipPlacement.Top -> layout.startY - toolTipSize.height
    ToolTipPlacement.Bottom -> layout.endY
}.toDp(density)