package com.pseudoankit.coachmark.overlay

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pseudoankit.coachmark.scope.CoachMarkScope

public object TransparentOverlayEffect : UnifyOverlayEffect {

    @Composable
    override fun CoachMarkScope.Background(
        content: @Composable () -> Unit
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            content()
        }
    }
}