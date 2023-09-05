package com.pseudoankit.coachmark.scope

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import com.pseudoankit.coachmark.model.HighlightedViewConfig
import com.pseudoankit.coachmark.model.OverlayClickEvent
import com.pseudoankit.coachmark.model.ToolTipPlacement
import com.pseudoankit.coachmark.model.TooltipConfig
import com.pseudoankit.coachmark.util.CoachMarkKey
import com.pseudoankit.coachmark.util.toPx

internal class CoachMarkScopeImpl(
    internal val onOverlayClicked: (CoachMarkKey) -> OverlayClickEvent,
    private val density: Density,
    private val layoutDirection: LayoutDirection
) : CoachMarkScope {

    private var _currentVisibleTooltip: TooltipConfig? by mutableStateOf(null)
    private var _lastVisibleTooltip: TooltipConfig? by mutableStateOf(null)
    private val _coachMarkItems = mutableMapOf<CoachMarkKey, TooltipConfig>()

    private var _visibleTooltips = listOf<TooltipConfig>()
        set(value) {
            _visibleTooltipIndex = 0
            updateVisibleItem(value, _visibleTooltipIndex)
            field = value
        }

    private var _visibleTooltipIndex = 0
        set(value) {
            updateVisibleItem(_visibleTooltips, value)
            field = value
        }

    override val lastVisibleTooltip: TooltipConfig?
        get() = _lastVisibleTooltip

    override val currentVisibleTooltip: TooltipConfig?
        get() = _currentVisibleTooltip

    override fun Modifier.enableCoachMark(
        key: CoachMarkKey,
        toolTipPlacement: ToolTipPlacement,
        tooltipAnimationSpec: AnimationSpec<Float>,
        highlightedViewConfig: HighlightedViewConfig,
    ): Modifier = onGloballyPositioned { layoutCoordinates ->
        val startPadding =
            highlightedViewConfig.padding.calculateStartPadding(layoutDirection).toPx(density)
        val topPadding = highlightedViewConfig.padding.calculateTopPadding().toPx(density)
        val endPadding =
            highlightedViewConfig.padding.calculateEndPadding(layoutDirection).toPx(density)
        val bottomPadding = highlightedViewConfig.padding.calculateBottomPadding().toPx(density)

        _coachMarkItems[key] = TooltipConfig(
            toolTipPlacement = toolTipPlacement,
            key = key,
            layout = TooltipConfig.Layout(
                width = layoutCoordinates.size.width + startPadding.toInt() + endPadding.toInt(),
                height = layoutCoordinates.size.height + topPadding.toInt() + bottomPadding.toInt(),
                startX = layoutCoordinates.positionInRoot().x - startPadding,
                startY = layoutCoordinates.positionInRoot().y - endPadding,
            ),
            highlightedViewShape = highlightedViewConfig.shape,
            animationState = TooltipConfig.AnimationState(
                tooltipAnimationSpec = tooltipAnimationSpec
            )
        )
    }

    override fun show(vararg keys: CoachMarkKey) {
        _visibleTooltips = keys.map { key ->
            _coachMarkItems[key]
                ?: throw NotImplementedError("definition for key $key not found, please provide key by using [Modifier.enableCoachMark]")
        }
    }

    override fun hide() {
        _visibleTooltips = listOf()
    }

    fun onOverlayClicked() {
        val item = currentVisibleTooltip ?: return

        when (onOverlayClicked(item.key)) {
            OverlayClickEvent.GoNext -> _visibleTooltipIndex++
            OverlayClickEvent.DismissAll -> hide()
            OverlayClickEvent.None -> {}
            OverlayClickEvent.GoPrevious -> _visibleTooltipIndex--
        }
    }

    private fun updateVisibleItem(items: List<TooltipConfig>, visibleTooltipIndex: Int) {
        _lastVisibleTooltip = _currentVisibleTooltip?.let {
            it.copy(
                animationState = it.animationState.copy(
                    initialAlpha = 1f,
                    targetAlpha = 0f
                )
            )
        }

        _currentVisibleTooltip = items.getOrNull(visibleTooltipIndex)?.let {
            it.copy(
                animationState = it.animationState.copy(
                    initialAlpha = 0f,
                    targetAlpha = 1f
                )
            )
        }
    }
}
