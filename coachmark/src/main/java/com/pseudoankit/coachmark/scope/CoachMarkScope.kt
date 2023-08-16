package com.pseudoankit.coachmark.scope

import androidx.compose.ui.Modifier
import com.pseudoankit.coachmark.model.UnifyCoachMarkConfig

public interface CoachMarkScope<KEY> {
    public fun Modifier.enableCoachMark(
        key: KEY,
        config: UnifyCoachMarkConfig<KEY>
    ): Modifier

    public fun hide()

    public fun show(vararg keys: KEY)
}