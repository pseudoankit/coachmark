package com.pseudoankit.coachmark.model

import androidx.compose.runtime.State

public data class TooltipHolder(
    val item: TooltipConfig,
    val alpha: State<Float>,
) {

    public val isVisible: Boolean get() = alpha.value > 0f
}