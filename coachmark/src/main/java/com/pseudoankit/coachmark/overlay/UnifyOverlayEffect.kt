package com.pseudoankit.coachmark.overlay

import androidx.compose.runtime.Composable
import com.pseudoankit.coachmark.scope.CoachMarkScope

public interface UnifyOverlayEffect {

    @Composable
    public fun CoachMarkScope.Background(content: @Composable () -> Unit)
}
