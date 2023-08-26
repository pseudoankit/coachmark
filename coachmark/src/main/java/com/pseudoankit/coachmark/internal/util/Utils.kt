package com.pseudoankit.coachmark.internal.util

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp

internal fun Int.toDp(density: Density) = toFloat().toDp(density)

internal fun Float.toDp(density: Density) = with(density) { toDp() }

internal fun Dp.toPx(density: Density) = with(density) { toPx() }

internal fun Modifier.clickable(
    showRipple: Boolean = true,
    onClick: () -> Unit
) = composed {
    clickable(
        indication = if (showRipple) LocalIndication.current else null,
        interactionSource = remember { MutableInteractionSource() },
        onClick = onClick
    )
}

internal fun buildPath(block: Path.() -> Unit): Path {
    return Path().apply {
        block()
        close()
    }
}