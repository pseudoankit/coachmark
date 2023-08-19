package com.pseudoankit.coachmark.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import com.pseudoankit.coachmark.model.CoachMarkGlobalConfig
import com.pseudoankit.coachmark.scope.CoachMarkScope
import com.pseudoankit.coachmark.scope.CoachMarkScopeImpl
import com.pseudoankit.coachmark.util.clickable
import com.pseudoankit.coachmark.util.toDp

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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(activeItem.overlay.color)
                    .clickable(
                        showRipple = false,
                        onClick = coachMark::onOverlayClicked
                    )
            ) {
                Box(
                    modifier = Modifier
                        .offset(
                            x = activeItem.tooltip.positionConifg.startX.toDp(LocalDensity.current),
                            y = activeItem.tooltip.positionConifg.startY.toDp(LocalDensity.current),
                        )
                        .then(activeItem.tooltip.modifier),
                    contentAlignment = Alignment.Center
                ) {
                    Text(color = activeItem.tooltip.textColor, text = activeItem.tooltip.text)
                }
            }
        }
    }
}
