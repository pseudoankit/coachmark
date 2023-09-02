package com.pseudoankit.coachmark.model

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pseudoankit.coachmark.util.CoachMarkDefaults
import com.pseudoankit.coachmark.util.buildPath
import com.pseudoankit.coachmark.util.toPx

public data class HighlightedViewConfig(
    val shape: Shape = Shape.RoundedRect(12.dp),
    val padding: PaddingValues = CoachMarkDefaults.HighlightedView.padding
) {
    public sealed interface Shape {
        public data class RoundedRect(val cornerRadius: Dp) : Shape
    }
}

internal fun HighlightedViewConfig.Shape.pathToHighlight(
    density: Density, size: Size
): Path = buildPath {
    when (this@pathToHighlight) {
        is HighlightedViewConfig.Shape.RoundedRect -> {
            addRoundRect(
                roundRect = RoundRect(
                    rect = Rect(
                        offset = Offset.Zero,
                        size = size
                    ),
                    cornerRadius = CornerRadius(cornerRadius.toPx(density))
                )
            )
        }
    }
}
