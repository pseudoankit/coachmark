package com.pseudoankit.coachmark

import androidx.compose.ui.Modifier

public interface CoachMarkScope<T> {
    public fun Modifier.enableCoachMark(
        key: T,
        config: UnifyCoachMarkConfig
    ): Modifier

    public fun hide(key: T)

    public fun show(key: T)

    public fun show(keys: List<T>)

    public fun hide(keys: List<T>)
}