package com.pseudoankit.coachmark.scope

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.pseudoankit.coachmark.model.CoachMarkConfig
import com.pseudoankit.coachmark.model.OverlayClickEvent
import com.pseudoankit.coachmark.util.CoachMarkKey

internal class CoachMarkScopeImpl(
//    private val globalConfig: CoachMarkGlobalConfig
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

//    override fun Modifier.enableCoachMark(
//        config: CoachMarkConfig
//    ): Modifier = onGloballyPositioned {
//        coachMarkItems[config.key] = mapToInternalConfig(
//            globalConfig = globalConfig,
//            config = config,
//            layoutCoordinates = it,
//        )
//    }

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
        when (item.overlay.onClick(item.key)) {
            OverlayClickEvent.GoNext -> visibleTooltipIndex++
            OverlayClickEvent.Dismiss -> hide()
            OverlayClickEvent.None -> {}
        }
    }
}
