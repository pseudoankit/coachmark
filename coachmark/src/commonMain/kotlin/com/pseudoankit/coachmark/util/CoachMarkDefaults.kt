package com.pseudoankit.coachmark.util

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pseudoankit.coachmark.model.OverlayClickEvent
import com.pseudoankit.coachmark.overlay.DimOverlayEffect
import com.pseudoankit.coachmark.overlay.UnifyOverlayEffect

/**
 * default values of coachmark items
 */
public object CoachMarkDefaults {
    public object Balloon {
        public val bgColor: Color get() = Color(0xFFA4A4EB)
        public val cornerRadius: Dp get() = 8.dp
        public val shadowElevation: Dp get() = 2.dp
        public val padding: PaddingValues get() = PaddingValues(horizontal = 8.dp, vertical = 6.dp)

        public object Arrow {
            public val height: Dp get() = 12.dp
            public val width: Dp get() = 16.dp
            public val bias: Float get() = 0.5f
        }
    }

    public object ToolTip {
        public val animationSpec: AnimationSpec<Float> get() = tween(ANIMATION_DURATION)
        public val paddingForTooltip: Dp = 8.dp
    }

    public object Overlay {
        public val background: UnifyOverlayEffect get() = DimOverlayEffect()
        public val clickEvent: OverlayClickEvent get() = OverlayClickEvent.GoNext
        public val animationSpec: AnimationSpec<Float> get() = tween(ANIMATION_DURATION)
    }

    public object HighlightedView {
        public val shape: Shape get() = RoundedCornerShape(12.dp)
        public val padding: PaddingValues get() = PaddingValues(8.dp)
    }
}
