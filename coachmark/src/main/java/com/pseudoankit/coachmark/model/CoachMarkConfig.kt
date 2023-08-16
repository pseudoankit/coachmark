package com.pseudoankit.coachmark.model

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.pseudoankit.coachmark.overlay.OverlayClickEvent
import com.pseudoankit.coachmark.util.CoachMarkKey

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
            modifier = Modifier
                .wrapContentSize()
                .clip(shape)
                .background(bgColor)
                .padding(padding),
            text = text
        )
    }
}
