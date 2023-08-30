package com.pseudoankit.coachmark

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.pseudoankit.coachmark.model.OverlayClickEvent
import com.pseudoankit.coachmark.overlay.UnifyOverlayBackground
import com.pseudoankit.coachmark.scope.CoachMarkScope
import com.pseudoankit.coachmark.scope.CoachMarkScopeImpl
import com.pseudoankit.coachmark.ui.CoachMarkImpl
import com.pseudoankit.coachmark.util.CoachMarkDefaults
import com.pseudoankit.coachmark.util.CoachMarkKey

@Composable
public fun UnifyCoachmark(
    overlayBackground: UnifyOverlayBackground = CoachMarkDefaults.Overlay.background,
    onOverlayClicked: (CoachMarkKey) -> OverlayClickEvent = { CoachMarkDefaults.Overlay.clickEvent },
    overlayContent: @Composable CoachMarkScope.(CoachMarkKey) -> Unit,
    content: @Composable CoachMarkScope.() -> Unit
) {
    val scope = remember(onOverlayClicked) {
        CoachMarkScopeImpl(onOverlayClicked = onOverlayClicked)
    }

    CoachMarkImpl(
        overlay = overlayBackground,
        content = content,
        scope = scope,
        overlayContent = overlayContent
    )
}
