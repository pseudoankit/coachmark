package com.pseudoankit.coachmark.overlay

import androidx.compose.runtime.Composable

public interface UnifyOverlayBackground {

    @Composable
    public fun Background(content: @Composable () -> Unit)
}