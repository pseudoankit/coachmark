package com.pseudoankit.coachmark.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.layoutId
import com.pseudoankit.coachmark.overlay.TooltipId
import com.pseudoankit.coachmark.overlay.UnifyOverlayEffect
import com.pseudoankit.coachmark.scope.CoachMarkScope
import com.pseudoankit.coachmark.scope.CoachMarkScopeImpl
import com.pseudoankit.coachmark.util.CoachMarkKey
import com.pseudoankit.coachmark.util.clickable
import com.pseudoankit.coachmark.util.rememberTooltipHolder

@Composable
internal fun CoachMarkImpl(
    overlayEffect: UnifyOverlayEffect,
    coachMarkScope: CoachMarkScopeImpl,
    tooltip: @Composable CoachMarkScope.(CoachMarkKey) -> Unit,
    content: @Composable (CoachMarkScope.() -> Unit),
) = with(overlayEffect) {
    val currentTooltip = coachMarkScope.currentVisibleTooltip?.let {
        rememberTooltipHolder(
            item = it,
            animationSpec = it.animationState.tooltipAnimationSpec,
            onAlphaValueUpdated = it.animationState.onAlphaValueUpdated
        )
    }

    val previousTooltip = coachMarkScope.lastVisibleTooltip?.let {
        rememberTooltipHolder(
            item = it,
            animationSpec = it.animationState.tooltipAnimationSpec,
            onAlphaValueUpdated = it.animationState.onAlphaValueUpdated
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        content(coachMarkScope)

        coachMarkScope.Overlay(
            modifier = Modifier
                .fillMaxSize()
                .run {
                    if (currentTooltip?.isVisible == true) {
                        clickable(showRipple = false, onClick = coachMarkScope::onOverlayClicked)
                    } else this
                }
                .alpha(
                    animateFloatAsState(
                        targetValue = if (currentTooltip?.isVisible == true) 1f else 0f,
                        animationSpec = overlayEffect.overlayAnimationSpec,
                        label = "OverlayAlphaAnimation"
                    ).value
                ),
            currentTooltip = currentTooltip,
            previousTooltip = previousTooltip
        ) {
            Tooltip(
                tooltipHolder = currentTooltip,
                modifier = Modifier.layoutId(TooltipId.current),
            ) {
                coachMarkScope.tooltip(it)
            }
            Tooltip(
                tooltipHolder = previousTooltip,
                modifier = Modifier.layoutId(TooltipId.previous),
            ) {
                coachMarkScope.tooltip(it)
            }
        }
    }
}
