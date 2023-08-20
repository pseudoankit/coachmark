package com.pseudoankit.coachmark.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import com.pseudoankit.coachmark.model.CoachMarkConfigInternal
import com.pseudoankit.coachmark.util.toDp

@Composable
internal fun Tooltip(
    activeItem: CoachMarkConfigInternal,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .offset(
                x = activeItem.tooltip.position.startX.toDp(LocalDensity.current),
                y = activeItem.tooltip.position.startY.toDp(LocalDensity.current),
            )
            .then(activeItem.tooltip.modifier),
        contentAlignment = Alignment.Center,
        content = content
    )
}