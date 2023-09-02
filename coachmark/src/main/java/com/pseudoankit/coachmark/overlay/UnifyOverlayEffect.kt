package com.pseudoankit.coachmark.overlay

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pseudoankit.coachmark.scope.CoachMarkScope

public interface UnifyOverlayEffect {

    private companion object {
        private const val ANIMATION_DURATION = 500
    }

    @Composable
    public fun CoachMarkScope.Overlay(
        modifier: Modifier,
        content: @Composable () -> Unit
    )

    public fun animationSpec(): AnimationSpec<Float> = tween(ANIMATION_DURATION)
}
