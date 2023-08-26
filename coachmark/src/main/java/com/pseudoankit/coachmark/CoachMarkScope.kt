package com.pseudoankit.coachmark

import androidx.compose.ui.Modifier
import com.pseudoankit.coachmark.model.CoachMarkConfig

public interface CoachMarkScope {
    public fun Modifier.enableCoachMark(
        config: CoachMarkConfig
    ): Modifier

    public fun hide()

    public fun show(vararg keys: CoachMarkKey)
}