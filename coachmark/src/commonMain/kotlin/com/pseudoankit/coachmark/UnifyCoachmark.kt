package com.pseudoankit.coachmark

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import com.pseudoankit.coachmark.model.OverlayClickEvent
import com.pseudoankit.coachmark.overlay.UnifyOverlayEffect
import com.pseudoankit.coachmark.scope.CoachMarkScope
import com.pseudoankit.coachmark.scope.CoachMarkScopeImpl
import com.pseudoankit.coachmark.scope.enableCoachMark
import com.pseudoankit.coachmark.ui.CoachMarkImpl
import com.pseudoankit.coachmark.util.CoachMarkDefaults
import com.pseudoankit.coachmark.util.CoachMarkKey

public val LocalCoachMarkScope: ProvidableCompositionLocal<CoachMarkScope> =
    compositionLocalOf { error("CompositionLocal CoachMarkScope not present") }

/**
 * Entry point to show coachmark,
 * This screen should to be called at the root level so that coachmark can be visible at very top
 * @param overlayEffect configure overlay effect to be shown when tooltip is visible
 * @param content actual screen content
 */
@Composable
public fun UnifyCoachmark(
    overlayEffect: UnifyOverlayEffect = CoachMarkDefaults.Overlay.background,
    onOverlayClicked: (CoachMarkKey) -> OverlayClickEvent = { CoachMarkDefaults.Overlay.clickEvent },
    content: @Composable CoachMarkScope.() -> Unit
) {
    UnifyCoachmark(
        tooltip = {},
        overlayEffect = overlayEffect,
        onOverlayClicked = onOverlayClicked,
        content = content
    )
}

@Deprecated(
    message = "Please avoid passing tooltip at top level, tooltips can be simply passed when calling `enableCoachMark` for each view",
)
/**
 * This method is deprecated, not recommended to pass tooltip at top level
 * as now it can be passed when calling [enableCoachMark] method for each view
 * for backward compatibility [tooltip] field will still work as expected
 * but [enableCoachMark]'s tooltip will take priority over [tooltip] if passed.
 *
 * Entry point to show coachmark,
 * This screen should to be called at the root level so that coachmark can be visible at very top
 * @param tooltip view to be shown when any view is highlighted
 * @param overlayEffect configure overlay effect to be shown when tooltip is visible
 * @param content actual screen content
 */
@Composable
public fun UnifyCoachmark(
    tooltip: @Composable CoachMarkScope.(CoachMarkKey) -> Unit,
    overlayEffect: UnifyOverlayEffect = CoachMarkDefaults.Overlay.background,
    onOverlayClicked: (CoachMarkKey) -> OverlayClickEvent = { CoachMarkDefaults.Overlay.clickEvent },
    content: @Composable CoachMarkScope.() -> Unit
) {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current

    val scope = remember(onOverlayClicked) {
        CoachMarkScopeImpl(
            onOverlayClicked = onOverlayClicked,
            density = density,
            layoutDirection = layoutDirection
        )
    }

    CompositionLocalProvider(LocalCoachMarkScope provides scope) {
        CoachMarkImpl(
            overlayEffect = overlayEffect,
            content = content,
            coachMarkScope = scope,
            tooltip = tooltip
        )
    }
}
