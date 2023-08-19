package com.pseudoankit.coachmark.model

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.pseudoankit.coachmark.overlay.OverlayClickEvent
import com.pseudoankit.coachmark.util.CoachMarkKey
import com.pseudoankit.coachmark.util.createToolTipModifier

public data class CoachMarkConfig(
    val tooltip: Tooltip,
    val overlay: Overlay = Overlay(),
    val key: CoachMarkKey
) {

    public data class Overlay(
        val color: Color? = null,
        val onClick: ((key: CoachMarkKey) -> OverlayClickEvent)? = null,
    )

    public data class Tooltip(
        val text: String,
        val textColor: Color? = null,
        val modifier: Modifier? = null
    ) {

        public constructor(
            text: String,
            textColor: Color,
            bgColor: Color,
            shape: Shape,
            padding: PaddingValues,
        ) : this(
            textColor = textColor,
            modifier = createToolTipModifier(bgColor, shape, padding),
            text = text
        )
    }
}
