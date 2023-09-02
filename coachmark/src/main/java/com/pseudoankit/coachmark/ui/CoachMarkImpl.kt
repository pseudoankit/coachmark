package com.pseudoankit.coachmark.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import com.pseudoankit.coachmark.overlay.UnifyOverlayEffect
import com.pseudoankit.coachmark.scope.CoachMarkScope
import com.pseudoankit.coachmark.scope.CoachMarkScopeImpl
import com.pseudoankit.coachmark.util.CoachMarkKey
import com.pseudoankit.coachmark.util.INVISIBLE_ALPHA
import com.pseudoankit.coachmark.util.VISIBLE_ALPHA
import com.pseudoankit.coachmark.util.clickable
import com.pseudoankit.coachmark.util.rememberTooltipHolder

@Composable
internal fun CoachMarkImpl(
    overlayEffect: UnifyOverlayEffect,
    coachMarkScope: CoachMarkScopeImpl,
    tooltip: @Composable CoachMarkScope.(CoachMarkKey) -> Unit,
    content: @Composable (CoachMarkScope.() -> Unit),
) = with(overlayEffect) {

    val currentVisibleTooltip by rememberTooltipHolder(
        item = coachMarkScope.currentVisibleTooltip,
        animationSpec = overlayEffect.tooltipAnimationSpec()
    )

    Box(modifier = Modifier.fillMaxSize()) {
        content(coachMarkScope)

        coachMarkScope.Overlay(
            modifier = Modifier
                .fillMaxSize()
                .run {
                    if (currentVisibleTooltip.alpha > INVISIBLE_ALPHA) {
                        clickable(showRipple = false, onClick = coachMarkScope::onOverlayClicked)
                    } else this
                }
                .alpha(
                    animateFloatAsState(
                        targetValue = if (currentVisibleTooltip.isVisible) VISIBLE_ALPHA else INVISIBLE_ALPHA,
                        animationSpec = overlayEffect.overlayAnimationSpec()
                    ).value
                )
        ) {
            Tooltip(currentVisibleTooltip) {
                coachMarkScope.tooltip(it)
            }
        }
    }
}
