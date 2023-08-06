package com.pseudoankit.coachmark

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

public object UnifyCoachMarkDefaults {
    public object Overlay {
        public val color: Color = Color.Black.copy(alpha = .75f)
        public val clickEvent: UnifyCoachMarkOverlayClickEvent =
            UnifyCoachMarkOverlayClickEvent.Dismiss
    }

    public object Item {
        public val textColor: Color = Color.White
        public val bgColor: Color = Color(0xFFA4A4EB)
        public val bgShape: RoundedCornerShape = RoundedCornerShape(16.dp)
        public val padding: PaddingValues = PaddingValues()
    }
}
