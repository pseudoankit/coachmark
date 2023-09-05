package com.pseudoankit.coachmark.overlay

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pseudoankit.coachmark.model.TooltipHolder
import com.pseudoankit.coachmark.scope.CoachMarkScope

/**
 * Contract to configure overlay effect to be shown when tooltip is visible
 *
 * @see DimOverlayEffect
 */
public interface UnifyOverlayEffect {


    /**
     * composable to draw the overlay view
     * @param modifier modifier that needs to be applied to the composable,
     * it already handles showing/hiding overlay and performing click event on it
     * @param currentTooltip denotes the currently visible tooltip
     * @param previousTooltip denotes the previous visible tooltip,
     * required to give a animation feel when switching to a new tooltip
     * @param content composable where necessary content will be drawn on top of overlay eg:tooltip
     * client needs to call this inside the top level composable for overlay
     */
    @Composable
    public fun CoachMarkScope.Overlay(
        modifier: Modifier,
        currentTooltip: TooltipHolder?,
        previousTooltip: TooltipHolder?,
        content: @Composable () -> Unit
    )

    /**
     * animation spec to be applied on overlay when showing/hiding a tooltip
     */
    public val overlayAnimationSpec: AnimationSpec<Float>
}
