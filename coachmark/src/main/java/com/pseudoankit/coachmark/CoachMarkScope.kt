package com.pseudoankit.coachmark

import androidx.compose.ui.Modifier

interface CoachMarkScope<T> {
    fun Modifier.enableCoachMark(
        key: T,
        config: UnifyCoachMarkConfig
    ): Modifier

    fun hide(key: T)

    fun show(key: T)

    fun show(keys: List<T>)

    fun hide(keys: List<T>)
}