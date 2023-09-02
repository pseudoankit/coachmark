package com.pseudoankit.coachmark.overlay

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pseudoankit.coachmark.scope.CoachMarkScope
import com.pseudoankit.coachmark.util.ANIMATION_DURATION

public interface UnifyOverlayEffect {

    @Composable
    public fun CoachMarkScope.Overlay(
        modifier: Modifier,
        content: @Composable () -> Unit
    )

    public fun overlayAnimationSpec(): AnimationSpec<Float> = tween(ANIMATION_DURATION)

    public fun tooltipAnimationSpec(): AnimationSpec<Float> = tween(ANIMATION_DURATION)
}
