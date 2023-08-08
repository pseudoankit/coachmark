package com.pseudoankit.coachmark

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.pseudoankit.coachmark.model.UnifyCoachMarkOverlayClickEvent

public object UnifyCoachMarkDefaults {
    public object Overlay {
        public val color: Color = Color.Black.copy(alpha = .75f)
        public val clickEvent: UnifyCoachMarkOverlayClickEvent =
            UnifyCoachMarkOverlayClickEvent.Dismiss
    }

    public object Item {
        public val textColor: Color = Color.White
        public val bgColor: Color = Color(0xFFA4A4EB)
        public val bgShape: Shape = RoundedCornerShape(16.dp)
        public val padding: PaddingValues = PaddingValues()
    }
}
