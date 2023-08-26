package com.pseudoankit.coachmark

import androidx.compose.runtime.Composable
import com.pseudoankit.coachmark.internal.ui.CoachMarkImpl
import com.pseudoankit.coachmark.model.CoachMarkGlobalConfig

@Composable
public fun UnifyCoachmark(
    config: CoachMarkGlobalConfig,
    content: @Composable CoachMarkScope.() -> Unit
) {
    CoachMarkImpl(
        content = content,
        config = config
    )
}
