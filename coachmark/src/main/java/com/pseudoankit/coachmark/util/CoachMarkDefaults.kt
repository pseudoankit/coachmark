package com.pseudoankit.coachmark.util

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pseudoankit.coachmark.overlay.OverlayClickEvent
import com.pseudoankit.coachmark.shape.ArrowToolTipShape

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
        public val shape: Shape = ArrowToolTipShape(com.pseudoankit.coachmark.shape.Arrow.Auto())

        public object Arrow {
            public val height: Dp = 15.dp
            public val width: Dp = 26.dp
        }
    }
}
