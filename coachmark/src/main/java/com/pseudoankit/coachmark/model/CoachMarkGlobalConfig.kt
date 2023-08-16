package com.pseudoankit.coachmark.model

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.pseudoankit.coachmark.util.CoachMarkDefaults
import com.pseudoankit.coachmark.util.CoachMarkKey

public data class CoachMarkGlobalConfig(
    val itemConfig: ItemConfig = ItemConfig(),
    val overlayConfig: OverlayConfig = OverlayConfig()
) {
    public data class OverlayConfig(
        val overlayColor: Color = CoachMarkDefaults.Overlay.color,
        val onOverlayClicked: (key: CoachMarkKey) -> CoachMarkOverlayClickEvent = { CoachMarkDefaults.Overlay.clickEvent },
    )

    public data class ItemConfig(
        val textColor: Color = CoachMarkDefaults.Item.textColor,
        val modifier: Modifier
    ) {

        public constructor(
            textColor: Color = CoachMarkDefaults.Item.textColor,
            bgColor: Color = CoachMarkDefaults.Item.bgColor,
            shape: Shape = CoachMarkDefaults.Item.bgShape,
            padding: PaddingValues = CoachMarkDefaults.Item.padding
        ) : this(
            textColor = textColor,
            modifier = Modifier
                .wrapContentSize()
                .clip(shape)
                .background(bgColor)
                .padding(padding)
        )
    }
}