package com.pseudoankit.coachmark.model

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.pseudoankit.coachmark.UnifyCoachMarkDefaults

/*
 * TODO
 * add cross icon
 */

public enum class UnifyCoachMarkOverlayClickEvent {
    GoNext, Dismiss, None
}

public data class UnifyCoachMarkGlobalConfig<KEY>(
    val itemConfig: ItemConfig = ItemConfig(),
    val overlayConfig: OverlayConfig<KEY> = OverlayConfig()
) {
    public data class OverlayConfig<KEY>(
        val overlayColor: Color = UnifyCoachMarkDefaults.Overlay.color,
        val onOverlayClicked: (key: KEY) -> UnifyCoachMarkOverlayClickEvent = { UnifyCoachMarkDefaults.Overlay.clickEvent },
    )

    public data class ItemConfig(
        val textColor: Color = UnifyCoachMarkDefaults.Item.textColor,
        val modifier: Modifier
    ) {

        public constructor(
            textColor: Color = UnifyCoachMarkDefaults.Item.textColor,
            bgColor: Color = UnifyCoachMarkDefaults.Item.bgColor,
            shape: Shape = UnifyCoachMarkDefaults.Item.bgShape,
            padding: PaddingValues = UnifyCoachMarkDefaults.Item.padding
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

public data class UnifyCoachMarkConfig<KEY>(
    val itemConfig: ItemConfig,
    val overlayConfig: OverlayConfig<KEY> = OverlayConfig()
) {
    public data class OverlayConfig<KEY>(
        val overlayColor: Color? = null,
        val onOverlayClicked: ((key: KEY) -> UnifyCoachMarkOverlayClickEvent)? = null,
    )

    public data class ItemConfig(
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

