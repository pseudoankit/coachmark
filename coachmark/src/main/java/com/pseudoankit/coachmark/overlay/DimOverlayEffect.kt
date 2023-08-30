package com.pseudoankit.coachmark.overlay

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import com.pseudoankit.coachmark.scope.CoachMarkScope

public class DimOverlayEffect(
    private val color: Color = Color.Black.copy(alpha = .75f)
) : UnifyOverlayEffect {

    @Composable
    override fun CoachMarkScope.Background(content: @Composable () -> Unit) {

        val alpha = animateFloatAsState(
            targetValue = if (currentVisibleTooltip == null) 0f else 1f,
            animationSpec = tween(500)
        ).value

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color)
                .graphicsLayer {
                    this.alpha = alpha
                }
        ) {
            content()
        }
    }
}