package com.pseudoankit.coachmark.overlay

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pseudoankit.coachmark.scope.CoachMarkScope

public interface UnifyOverlayEffect {

    @Composable
    public fun CoachMarkScope.Overlay(
        modifier: Modifier,
        content: @Composable () -> Unit
    )
}
