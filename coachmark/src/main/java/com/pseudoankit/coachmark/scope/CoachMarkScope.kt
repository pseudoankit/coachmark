package com.pseudoankit.coachmark.scope

import com.pseudoankit.coachmark.util.CoachMarkKey

public interface CoachMarkScope {
//    public fun Modifier.enableCoachMark(
//        config: CoachMarkConfig
//    ): Modifier

    public fun hide()

    public fun show(vararg keys: CoachMarkKey)
}