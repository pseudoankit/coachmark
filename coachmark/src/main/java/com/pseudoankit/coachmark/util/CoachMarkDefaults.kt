package com.pseudoankit.coachmark.util

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
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
        public val shape: Shape = RoundedCornerShape(16.dp)
        public val padding: PaddingValues = PaddingValues()
    }
}
