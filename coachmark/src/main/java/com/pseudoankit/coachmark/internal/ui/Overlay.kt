package com.pseudoankit.coachmark.internal.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pseudoankit.coachmark.internal.CoachMarkConfigInternal
import com.pseudoankit.coachmark.internal.util.clickable

@Composable
internal fun Overlay(
    activeItem: CoachMarkConfigInternal,
    onOverlayClicked: () -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(activeItem.overlay.color)
            .clickable(
                showRipple = false,
                onClick = onOverlayClicked
            ),
        content = content
    )
}