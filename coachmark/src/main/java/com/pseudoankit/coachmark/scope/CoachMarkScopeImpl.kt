package com.pseudoankit.coachmark.scope

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import com.pseudoankit.coachmark.model.CoachMarkConfig
import com.pseudoankit.coachmark.model.OverlayClickEvent
import com.pseudoankit.coachmark.model.ToolTipPlacement
import com.pseudoankit.coachmark.util.CoachMarkKey

internal class CoachMarkScopeImpl(
    internal val onOverlayClicked: (CoachMarkKey) -> OverlayClickEvent
) : CoachMarkScope {

    var currentVisibleTooltip: CoachMarkConfig? by mutableStateOf(null)

    private val coachMarkItems = mutableMapOf<CoachMarkKey, CoachMarkConfig>()

    private var visibleTooltips = listOf<CoachMarkConfig>()
        set(value) {
            visibleTooltipIndex = 0
            currentVisibleTooltip = value.getOrNull(visibleTooltipIndex)
            field = value
        }

    private var visibleTooltipIndex = 0
        set(value) {
            currentVisibleTooltip = visibleTooltips.getOrNull(value)
            field = value
        }

    override fun Modifier.enableCoachMark(
        key: CoachMarkKey,
        toolTipPlacement: ToolTipPlacement
    ): Modifier = onGloballyPositioned { layoutCoordinates ->
        coachMarkItems[key] = CoachMarkConfig(
            toolTipPlacement = toolTipPlacement,
            key = key,
            layout = CoachMarkConfig.Layout(
                width = layoutCoordinates.size.width,
                height = layoutCoordinates.size.height,
                startX = layoutCoordinates.positionInRoot().x,
                startY = layoutCoordinates.positionInRoot().y,
            )
        )
    }

    override fun show(vararg keys: CoachMarkKey) {
        visibleTooltips = keys.map { key ->
            coachMarkItems[key]
                ?: throw NotImplementedError("definition for key $key not found, please provide key by using [Modifier.enableCoachMark]")
        }
    }

    override fun hide() {
        visibleTooltips = listOf()
    }

    fun onOverlayClicked() {
        val item = currentVisibleTooltip ?: return

        when (onOverlayClicked(item.key)) {
            OverlayClickEvent.GoNext -> visibleTooltipIndex++
            OverlayClickEvent.DismissAll -> hide()
            OverlayClickEvent.None -> {}
            OverlayClickEvent.GoPrevious -> visibleTooltipIndex--
        }
    }
}
