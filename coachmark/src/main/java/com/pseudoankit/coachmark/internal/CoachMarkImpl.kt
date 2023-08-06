package com.pseudoankit.coachmark.internal

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.pseudoankit.coachmark.CoachMarkScope
import com.pseudoankit.coachmark.UnifyCoachMarkGlobalConfig

@Composable
internal fun <T> CoachMarkImpl(
    globalCoachMarkConfig: UnifyCoachMarkGlobalConfig = UnifyCoachMarkGlobalConfig(),
    content: @Composable CoachMarkScope<T>.() -> Unit
) {
    val coachMark = remember { CoachMarkScopeImpl<T>(globalCoachMarkConfig) }

    Box {
        content(coachMark)

    }
}