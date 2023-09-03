package com.pseudoankit.coachmark.overlay

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import com.pseudoankit.coachmark.model.TooltipHolder
import com.pseudoankit.coachmark.scope.CoachMarkScope
import com.pseudoankit.coachmark.util.highlightActualView

public class DimOverlayEffect(
    private val color: Color = Color.Black.copy(alpha = .75f)
) : UnifyOverlayEffect {

    @Composable
    override fun CoachMarkScope.Overlay(
        modifier: Modifier,
        currentTooltip: TooltipHolder,
        previousTooltip: TooltipHolder,
        content: @Composable () -> Unit
    ) {

        val density = LocalDensity.current

        Box(
            modifier = modifier
                .graphicsLayer(alpha = .99f)
                .drawBehind {
                    drawRect(color)
                    currentTooltip.item?.let { tooltip ->
                        highlightActualView(tooltip, density, currentTooltip.alpha.value)
                    }
                    previousTooltip.item?.let { tooltip ->
                        highlightActualView(tooltip, density, previousTooltip.alpha.value)
                    }
                }
        ) {
            content()
        }
    }


}