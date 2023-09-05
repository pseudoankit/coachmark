package com.pseudoankit.coachmark.model

/**
 * tooltip holder containing info when tooltip is visible
 * @param item actual tooltip visible at the moment
 * @param alpha current alpha value that needs to be added in view to animate
 */
public data class TooltipHolder(
    val item: TooltipConfig,
    val alpha: Float,
) {

    /**
     * denotes if tooltip is currently visible or not
     */
    public val isVisible: Boolean get() = alpha > 0f
}