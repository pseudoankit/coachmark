package com.pseudoankit.coachmark.internal

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.layout.onGloballyPositioned
import com.pseudoankit.coachmark.CoachMarkKey
import com.pseudoankit.coachmark.CoachMarkScope
import com.pseudoankit.coachmark.internal.util.mapToInternalConfig
import com.pseudoankit.coachmark.model.CoachMarkConfig
import com.pseudoankit.coachmark.model.CoachMarkGlobalConfig
import com.pseudoankit.coachmark.model.OverlayClickEvent

internal class CoachMarkScopeImpl(
    private val globalConfig: CoachMarkGlobalConfig
) : CoachMarkScope {

    var currentVisibleTooltip: CoachMarkConfigInternal? by mutableStateOf(null)

    private val coachMarkItems = mutableMapOf<CoachMarkKey, CoachMarkConfigInternal>()

    private var visibleTooltips = listOf<CoachMarkConfigInternal>()
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

    @SuppressLint("UnnecessaryComposedModifier")
    override fun Modifier.enableCoachMark(
        config: CoachMarkConfig
    ): Modifier = composed {
        onGloballyPositioned {
            coachMarkItems[config.key] = mapToInternalConfig(
                globalConfig = globalConfig,
                config = config,
                layoutCoordinates = it,
            )
        }
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
        when (item.overlay.onClick(item.key)) {
            OverlayClickEvent.GoNext -> visibleTooltipIndex++
            OverlayClickEvent.Dismiss -> hide()
            OverlayClickEvent.None -> {}
        }
    }
}
