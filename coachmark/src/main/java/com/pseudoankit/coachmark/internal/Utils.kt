package com.pseudoankit.coachmark.internal

import androidx.compose.ui.unit.Density

internal fun Float.toDp(density: Density) = with(density) { toDp() }