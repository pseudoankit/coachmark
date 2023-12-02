package com.pseudoankit.coachmark.model

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.runtime.Stable
import com.pseudoankit.coachmark.scope.CoachMarkScope
import com.pseudoankit.coachmark.util.CoachMarkKey

/**
 * actual tooltip config containing all details about tooltip and it's view on which tooltip is shown
 * @param layout layout configuration/coordinates of the view
 * @param toolTipPlacement denotes placement of tooltip w.r.t  view
 * @param key key of a view where enabling tooltip, used to show/hide on the basis of key
 * basically it acts a unique identifier of the view/tooltip
 * @param highlightedViewShape configuration of the view when tooltip is showing/active for the view
 * @param animationState contains animation configs for a view when showing/hiding a tooltip
 */
@Stable
public data class TooltipConfig(
    val layout: Layout,
    val toolTipPlacement: ToolTipPlacement,
    val key: CoachMarkKey,
    val highlightedViewShape: HighlightedViewConfig.Shape,
    val animationState: AnimationState
) {

    /**
     * layout configurations of the actual view
     * it contains coordinates/size of the view to be highlighted, it is calculated by consuming
     * onGloballyPositioned callback on modifier, it also adds up [HighlightedViewConfig] passed by client
     * when adding coachmark modifier [CoachMarkScope.enableCoachMark]
     *
     * @param width width of the view including [HighlightedViewConfig]
     * @param height height of the view including [HighlightedViewConfig]
     * @param startX starting x coordinate of the view including [HighlightedViewConfig]
     * @param startY starting y coordinate of the view including [HighlightedViewConfig]
     */
    @Stable
    public data class Layout(
        val width: Int,
        val height: Int,
        val startX: Float,
        val startY: Float,
    ) {

        /**
         * end x coordinate of the view which is [startX] + [width]
         */
        val endX: Float = startX + width

        /**
         * end y coordinate of the view which is [startY] + [height]
         */
        val endY: Float = startY + height
    }

    /**
     * denotes animation state when showing/hiding a tooltip
     * this is used to animate the view's alpha staring from [initialAlpha] to [targetAlpha]
     *
     * @param initialAlpha denotes the starting alpha value when visibility changes
     * @param targetAlpha denotes the ending alpha value when visibility changes
     * @param tooltipAnimationSpec denotes the animation that needs to be applied when changing visibility of tooltip
     * @param onAlphaValueUpdated callback when alpha value is animating from [initialAlpha] to [targetAlpha]
     */
    @Stable
    public data class AnimationState(
        val initialAlpha: Float = 0f,
        val targetAlpha: Float = 0f,
        val tooltipAnimationSpec: AnimationSpec<Float>,
        val onAlphaValueUpdated: (Float) -> Unit = {}
    )
}
