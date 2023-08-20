package com.pseudoankit.coachmark.util

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.pseudoankit.coachmark.shape.Arrow
import com.pseudoankit.coachmark.shape.ArrowToolTipShape

internal fun Modifier.toolTipContentPadding(shape: Shape): Modifier = run {
    val arrow = (shape as? ArrowToolTipShape)?.arrow ?: return@run this
    padding(
        top = arrow.topPadding(),
        bottom = arrow.bottomPadding(),
        start = arrow.leftPadding(),
        end = arrow.rightPadding()
    )
}

internal fun Arrow.leftPadding() = when (this) {
    is Arrow.Bottom -> 0.dp
    is Arrow.End -> 0.dp
    is Arrow.Start -> width
    is Arrow.Top -> 0.dp
}

internal fun Arrow.rightPadding() = when (this) {
    is Arrow.Bottom -> 0.dp
    is Arrow.End -> width
    is Arrow.Start -> 0.dp
    is Arrow.Top -> 0.dp
}

internal fun Arrow.bottomPadding() = when (this) {
    is Arrow.Bottom -> height
    is Arrow.End -> 0.dp
    is Arrow.Start -> 0.dp
    is Arrow.Top -> 0.dp
}

internal fun Arrow.topPadding() = when (this) {
    is Arrow.Bottom -> 0.dp
    is Arrow.End -> 0.dp
    is Arrow.Start -> 0.dp
    is Arrow.Top -> height
}
