package com.pseudoankit.coachmark.scope

import androidx.compose.ui.Modifier
import com.pseudoankit.coachmark.model.ToolTipPlacement
import com.pseudoankit.coachmark.util.CoachMarkKey

public interface CoachMarkScope {
    public fun Modifier.enableCoachMark(
        key: CoachMarkKey,
        toolTipPlacement: ToolTipPlacement
    ): Modifier

    public fun hide()

    public fun show(vararg keys: CoachMarkKey)
}