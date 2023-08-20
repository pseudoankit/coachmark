package com.pseudoankit.coachmark.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.pseudoankit.coachmark.model.CoachMarkGlobalConfig
import com.pseudoankit.coachmark.scope.CoachMarkScope
import com.pseudoankit.coachmark.scope.CoachMarkScopeImpl

@Composable
internal fun CoachMarkImpl(
    config: CoachMarkGlobalConfig,
    modifier: Modifier = Modifier,
    content: @Composable CoachMarkScope.() -> Unit
) {
    val coachMark = remember(config) {
        CoachMarkScopeImpl(globalConfig = config)
    }

    val activeItem = coachMark.currentVisibleTooltip

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        content(coachMark)

        if (activeItem != null) {
            Overlay(
                activeItem = activeItem,
                onOverlayClicked = coachMark::onOverlayClicked
            ) {
                Tooltip(activeItem = activeItem) {
                    Text(
                        color = activeItem.tooltip.textColor,
                        text = activeItem.tooltip.text
                    )
                }
            }
        }
    }
}
