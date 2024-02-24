package com.pseudoankit.coachmark.util

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.pseudoankit.coachmark.model.TooltipConfig
import com.pseudoankit.coachmark.model.TooltipHolder

/**
 * utility to create a tooltip holder which wraps the tooltip along with alpha of the view to animate
 * @param onAlphaValueUpdated callback when alpha value is animating from initial to target alpha
 */
@Composable
internal fun rememberTooltipHolder(
    item: TooltipConfig,
    animationSpec: AnimationSpec<Float>,
    onAlphaValueUpdated: (Float) -> Unit
): TooltipHolder {

    var alpha by rememberMutableStateOf(
        value = item.animationState.initialAlpha,
        item
    )

    LaunchedEffect(item) {
        animate(
            initialValue = item.animationState.initialAlpha,
            targetValue = item.animationState.targetAlpha,
            animationSpec = animationSpec,
            block = { value, _ ->
                alpha = value
                onAlphaValueUpdated(value)
            }
        )
    }

    return remember(item, alpha) {
        TooltipHolder(
            item = item,
            alpha = alpha,
        )
    }
}