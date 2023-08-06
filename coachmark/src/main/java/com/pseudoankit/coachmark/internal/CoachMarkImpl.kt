package com.pseudoankit.coachmark.internal

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import com.pseudoankit.coachmark.CoachMarkScope
import com.pseudoankit.coachmark.UnifyCoachMarkGlobalConfig

@Composable
internal fun <T> CoachMarkImpl(
    globalCoachMarkConfig: UnifyCoachMarkGlobalConfig = UnifyCoachMarkGlobalConfig(),
    content: @Composable CoachMarkScope<T>.() -> Unit
) {
    val coachMark = remember { CoachMarkScopeImpl<T>(globalCoachMarkConfig) }
    val item = coachMark.activeItem

    Box(
        modifier = Modifier
            .fillMaxSize()
            .run {
                if (item != null) {
                    background(item.overlayConfig.overlayColor)
                } else this
            }
    ) {
        content(coachMark)

        if (item != null) {
            Box(
                modifier = Modifier
                    .offset(
                        x = item.coordinate.x.toDp(LocalDensity.current),
                        y = item.coordinate.y.toDp(LocalDensity.current),
                    )
                    .then(item.itemConfig.modifier),
                contentAlignment = Alignment.Center
            ) {
                Text(color = item.itemConfig.textColor, text = item.itemConfig.text)
            }
        }
    }
}