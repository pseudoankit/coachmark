package com.pseudoankit.coachmark.model

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.pseudoankit.coachmark.CoachMarkDefaults
import com.pseudoankit.coachmark.CoachMarkKey
import com.pseudoankit.coachmark.internal.util.createToolTipModifier

public data class CoachMarkGlobalConfig(
    val overlay: Overlay,
    val tooltip: Tooltip,
) {

    public data class Overlay(
        val color: Color = CoachMarkDefaults.Overlay.color,
        val onClick: (key: CoachMarkKey) -> OverlayClickEvent = { CoachMarkDefaults.Overlay.clickEvent },
    )

    public data class Tooltip(
        val textColor: Color = CoachMarkDefaults.Tooltip.textColor,
        val modifier: Modifier = Modifier
    ) {

        public constructor(
            shape: Shape,
            textColor: Color = CoachMarkDefaults.Tooltip.textColor,
            bgColor: Color = CoachMarkDefaults.Tooltip.bgColor,
            padding: PaddingValues = CoachMarkDefaults.Tooltip.padding
        ) : this(
            textColor = textColor,
            modifier = createToolTipModifier(bgColor, shape, padding)
        )
    }
}
