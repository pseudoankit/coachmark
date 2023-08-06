package com.pseudoankit.coachmark

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object UnifyCoachMarkDefaults {
    object Overlay {
        val color = Color.Black.copy(alpha = .40f)
        val clickEvent = UnifyCoachMarkOverlayClickEvent.None
    }


    object Item {
        val textColor = Color.White
        val bgColor = Color(0xFFA4A4EB)
        val bgShape = RoundedCornerShape(16.dp)
        val padding = PaddingValues()
    }
}
