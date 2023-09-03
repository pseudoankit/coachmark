package com.pseudoankit.coachmark.util

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.pseudoankit.coachmark.model.TooltipConfig
import com.pseudoankit.coachmark.model.TooltipConfig.State.Visible
import com.pseudoankit.coachmark.model.TooltipHolder

@Composable
internal fun rememberTooltipHolder(
    item: TooltipConfig?,
    animationSpec: AnimationSpec<Float>,
): TooltipHolder {
    var actualItem by rememberMutableStateOf(value = item)
    var state by rememberMutableStateOf(value = item?.animationState?.from)

    LaunchedEffect(item) {
        if (item != null) {
            actualItem = item
        }
    }
    LaunchedEffect(Unit) {
        state = item?.animationState?.to
    }

    val alpha = animateFloatAsState(
        targetValue = if (state == Visible) 1f else 0f,
        animationSpec = animationSpec,
        finishedListener = {
            if (it == INVISIBLE_ALPHA) {
                // todo actual item to null
            }
        }
    )

    return remember(alpha, actualItem) {
        TooltipHolder(
            item = actualItem,
            alpha = alpha,
        )
    }
}