package com.pseudoankit.coachmark.model

public data class TooltipHolder(
    val item: TooltipConfig,
    val alpha: Float,
) {

    public val isVisible: Boolean get() = alpha > 0f
}