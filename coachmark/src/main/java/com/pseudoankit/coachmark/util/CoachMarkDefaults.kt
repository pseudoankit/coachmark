package com.pseudoankit.coachmark.util

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pseudoankit.coachmark.overlay.OverlayClickEvent

public object CoachMarkDefaults {
    public object Overlay {
        public val color: Color = Color.Transparent
        public val clickEvent: OverlayClickEvent = OverlayClickEvent.GoNext
    }

    public object Tooltip {
        public val textColor: Color = Color.White
        public val bgColor: Color = Color(0xFFA4A4EB)
        public val cornerRadius: Dp = 12.dp
        public val padding: PaddingValues = PaddingValues()

        public object Arrow {
            public val height: Dp = 22.dp
            public val width: Dp = 40.dp
        }
    }
}
