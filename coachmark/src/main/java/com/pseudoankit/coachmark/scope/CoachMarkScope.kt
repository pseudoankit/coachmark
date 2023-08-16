package com.pseudoankit.coachmark.scope

import androidx.compose.ui.Modifier
import com.pseudoankit.coachmark.model.CoachMarkConfig
import com.pseudoankit.coachmark.util.CoachMarkKey

public interface CoachMarkScope {
    public fun Modifier.enableCoachMark(
        config: CoachMarkConfig
    ): Modifier

    public fun hide()

    public fun show(vararg keys: CoachMarkKey)
}