package com.pseudoankit.coachmark.util

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.pseudoankit.coachmark.model.TooltipConfig
import com.pseudoankit.coachmark.model.TooltipHolder

@Composable
internal fun rememberTooltipHolder(
    item: TooltipConfig,
    animationSpec: AnimationSpec<Float>,
): TooltipHolder {

    val alpha = rememberMutableStateOf(
        value = item.animationState.initialAlpha,
        item.animationState.initialAlpha
    )

    LaunchedEffect(item) {
        animate(
            initialValue = item.animationState.initialAlpha,
            targetValue = item.animationState.targetAlpha,
            animationSpec = animationSpec,
            block = { value, velocity ->
                alpha.value = value
            }
        )
    }

    return remember(alpha, item) {
        TooltipHolder(
            item = item,
            alpha = alpha,
        )
    }
}