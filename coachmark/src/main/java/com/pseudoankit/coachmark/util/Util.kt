package com.pseudoankit.coachmark.util

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp

internal const val INVISIBLE_ALPHA = 0f
internal const val VISIBLE_ALPHA = 1f
internal const val ANIMATION_DURATION = 500

internal fun Int.toDp(density: Density) = toFloat().toDp(density)

internal fun Float.toDp(density: Density) = with(density) { toDp() }

internal fun Dp.toPx(density: Density) = with(density) { toPx() }

@Composable
internal fun <T> rememberMutableStateOf(value: T, key: Any = true) = remember(key) {
    mutableStateOf(value)
}

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

internal fun coachMarkLog(vararg log: String) {
    println("UnifyCoachMarkLogs : ${log.joinToString(", ")}")
}