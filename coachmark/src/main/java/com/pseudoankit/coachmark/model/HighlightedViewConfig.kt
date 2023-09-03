package com.pseudoankit.coachmark.model

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pseudoankit.coachmark.util.CoachMarkDefaults
import com.pseudoankit.coachmark.util.buildPath
import com.pseudoankit.coachmark.util.toPx

public data class HighlightedViewConfig(
    val shape: Shape = Shape.Rect(12.dp),
    val padding: PaddingValues = CoachMarkDefaults.HighlightedView.padding
) {

    public sealed interface Shape {
        public fun pathToHighlight(density: Density, size: Size): Path

        public data class Rect(val cornerRadius: Dp) : Shape {
            override fun pathToHighlight(density: Density, size: Size): Path = buildPath {
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
}
