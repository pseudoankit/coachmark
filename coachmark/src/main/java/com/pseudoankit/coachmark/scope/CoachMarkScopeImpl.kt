package com.pseudoankit.coachmark.scope

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import com.pseudoankit.coachmark.model.CoachMarkConfigInternal
import com.pseudoankit.coachmark.model.UnifyCoachMarkConfig
import com.pseudoankit.coachmark.model.UnifyCoachMarkGlobalConfig
import com.pseudoankit.coachmark.model.UnifyCoachMarkOverlayClickEvent
import com.pseudoankit.coachmark.util.mapToInternalConfig

internal class CoachMarkScopeImpl<KEY>(
    private val globalConfig: UnifyCoachMarkGlobalConfig<KEY>
) : CoachMarkScope<KEY> {

    private val coachMarkItems = mutableMapOf<KEY, CoachMarkConfigInternal<KEY>>()

    private var activeItems = listOf<CoachMarkConfigInternal<KEY>>()
        set(value) {
            activeItem = value.getOrNull(activeItemIndex)
            field = value
        }

    private var activeItemIndex = 0
        set(value) {
            activeItem = activeItems.getOrNull(value)
            field = value
        }

    var activeItem: CoachMarkConfigInternal<KEY>? by mutableStateOf(null)

    override fun Modifier.enableCoachMark(
        key: KEY,
        config: UnifyCoachMarkConfig<KEY>
    ): Modifier = composed {
        onGloballyPositioned {
            val coordinates = Offset(
                x = it.positionInRoot().x,
                y = it.positionInRoot().y + it.size.height
            )

            coachMarkItems[key] = mapToInternalConfig(
                globalConfig, config, coordinates, key
            )
        }
    }

    override fun show(vararg keys: KEY) {
        activeItems = keys.map {
            coachMarkItems[it] ?: throw NotImplementedError("definition for key=$it not found")
        }
        activeItemIndex = 0
    }

    override fun hide() {
        activeItems = listOf()
    }

    fun onOverlayClicked() {
        val item = activeItem ?: return
        when (item.overlayConfig.onOverlayClicked(item.key)) {
            UnifyCoachMarkOverlayClickEvent.GoNext -> activeItemIndex++
            UnifyCoachMarkOverlayClickEvent.Dismiss -> hide()
            UnifyCoachMarkOverlayClickEvent.None -> {}
        }
    }
}
