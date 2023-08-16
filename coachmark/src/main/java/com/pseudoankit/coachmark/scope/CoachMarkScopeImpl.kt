package com.pseudoankit.coachmark.scope

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import com.pseudoankit.coachmark.model.CoachMarkConfig
import com.pseudoankit.coachmark.model.CoachMarkConfigInternal
import com.pseudoankit.coachmark.model.CoachMarkGlobalConfig
import com.pseudoankit.coachmark.model.CoachMarkOverlayClickEvent
import com.pseudoankit.coachmark.util.CoachMarkKey
import com.pseudoankit.coachmark.util.mapToInternalConfig

internal class CoachMarkScopeImpl(
    private val globalConfig: CoachMarkGlobalConfig
) : CoachMarkScope {

    private val coachMarkItems = mutableMapOf<CoachMarkKey, CoachMarkConfigInternal>()

    private var activeItems = listOf<CoachMarkConfigInternal>()
        set(value) {
            activeItem = value.getOrNull(activeItemIndex)
            field = value
        }

    private var activeItemIndex = 0
        set(value) {
            activeItem = activeItems.getOrNull(value)
            field = value
        }

    var activeItem: CoachMarkConfigInternal? by mutableStateOf(null)

    override fun Modifier.enableCoachMark(
        key: CoachMarkKey,
        config: CoachMarkConfig
    ): Modifier = composed {
        onGloballyPositioned {
            val coordinates = Offset(
                x = it.positionInRoot().x,
                y = it.positionInRoot().y + it.size.height
            )

            coachMarkItems[key as Any] = mapToInternalConfig(
                globalConfig, config, coordinates, key
            )
        }
    }

    override fun show(vararg keys: CoachMarkKey) {
        activeItems = keys.map {
            coachMarkItems[it] ?: throw NotImplementedError("definition for Any=$it not found")
        }
        activeItemIndex = 0
    }

    override fun hide() {
        activeItems = listOf()
    }

    fun onOverlayClicked() {
        val item = activeItem ?: return
        when (item.overlayConfig.onOverlayClicked(item.key)) {
            CoachMarkOverlayClickEvent.GoNext -> activeItemIndex++
            CoachMarkOverlayClickEvent.Dismiss -> hide()
            CoachMarkOverlayClickEvent.None -> {}
        }
    }
}
