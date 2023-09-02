package com.pseudoankit.coachmark.util

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.pseudoankit.coachmark.model.TooltipConfig

internal data class TooltipHolder(
    val item: TooltipConfig?,
    val alpha: Float,
    val isVisible: Boolean
)

@Composable
internal fun rememberTooltipHolder(
    item: TooltipConfig?,
    animationSpec: AnimationSpec<Float>
): State<TooltipHolder> {
    var actualItem by rememberMutableStateOf(value = item)

    var isVisible by rememberMutableStateOf(value = false)
    LaunchedEffect(item) {
        if (item == null) {
            isVisible = false
        } else {
            isVisible = true
            actualItem = item
        }
    }

    val alpha = animateFloatAsState(
        targetValue = if (isVisible) VISIBLE_ALPHA else INVISIBLE_ALPHA,
        animationSpec = animationSpec,
        finishedListener = {
            if (it == INVISIBLE_ALPHA) {
                // todo actual item to null
            }
        }
    ).value

    return remember(alpha, actualItem) {
        derivedStateOf {
            TooltipHolder(
                item = actualItem,
                alpha = alpha,
                isVisible = isVisible
            )
        }
    }
}