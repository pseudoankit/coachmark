package com.pseudoankit.coachmark.overlay

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.pseudoankit.coachmark.scope.CoachMarkScope

public class DimOverlayEffect(
    private val color: Color = Color.Black.copy(alpha = .75f)
) : UnifyOverlayEffect {

    @Composable
    override fun CoachMarkScope.Overlay(
        modifier: Modifier,
        content: @Composable () -> Unit
    ) {

        Box(
            modifier = modifier.background(color)
        ) {
            content()
        }
    }
}