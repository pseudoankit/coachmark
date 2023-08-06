package com.pseudoankit.coachmark.internal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import com.pseudoankit.coachmark.CoachMarkScope
import com.pseudoankit.coachmark.UnifyCoachMarkConfig
import com.pseudoankit.coachmark.UnifyCoachMarkGlobalConfig

internal class CoachMarkScopeImpl<T>(
    private val globalConfig: UnifyCoachMarkGlobalConfig
) : CoachMarkScope<T> {

    private val coachMarkItems = mutableMapOf<T, CoachMarkConfigInternal<T>>()

    var visibleItems by mutableStateOf(listOf<CoachMarkConfigInternal<T>>())

    override fun Modifier.enableCoachMark(
        key: T,
        config: UnifyCoachMarkConfig
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

    override fun hide(keys: List<T>) {
        val itemsToRemove = keys.toSet()
        visibleItems = visibleItems.mapNotNull {
            if (itemsToRemove.contains(it.key)) null else it
        }
    }

    override fun show(keys: List<T>) {
        visibleItems = buildList {
            addAll(visibleItems)
            addAll(
                keys.map {
                    coachMarkItems[it] ?: throw NotImplementedError("definition of $it not found")
                }
            )
        }
    }

    override fun hide(key: T) {
        hide(listOf(key))
    }

    override fun show(key: T) {
        show(listOf(key))
    }
}