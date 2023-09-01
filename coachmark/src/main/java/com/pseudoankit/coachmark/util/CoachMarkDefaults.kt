package com.pseudoankit.coachmark.util

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pseudoankit.coachmark.model.OverlayClickEvent
import com.pseudoankit.coachmark.overlay.DimOverlayEffect
import com.pseudoankit.coachmark.overlay.UnifyOverlayEffect

public object CoachMarkDefaults {
    public object Balloon {
        public val bgColor: Color = Color(0xFFA4A4EB)
        public val cornerRadius: Dp = 8.dp
        public val shadowElevation: Dp = 2.dp
        public val padding: PaddingValues = PaddingValues(horizontal = 8.dp, vertical = 6.dp)

        public object Arrow {
            public val height: Dp = 12.dp
            public val width: Dp = 16.dp
            public const val bias: Float = 0.5f
        }
    }

    public object Overlay {
        public val background: UnifyOverlayEffect = DimOverlayEffect()
        public val clickEvent: OverlayClickEvent = OverlayClickEvent.GoNext
    }
}
