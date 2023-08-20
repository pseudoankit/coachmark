package com.pseudoankit.coachmark.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pseudoankit.coachmark.model.CoachMarkConfigInternal
import com.pseudoankit.coachmark.util.clickable

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