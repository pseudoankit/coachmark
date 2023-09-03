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
    item: TooltipConfig,
    animationSpec: AnimationSpec<Float>,
): TooltipHolder {

    var state by rememberMutableStateOf(value = item.animationState.from, item.animationState)

    LaunchedEffect(item.animationState) {
        state = item.animationState.to
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

    return remember(alpha, item) {
        TooltipHolder(
            item = item,
            alpha = alpha,
        )
    }
}