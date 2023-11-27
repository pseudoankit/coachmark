package com.pseudoankit.coachmark.overlay

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import com.pseudoankit.coachmark.model.TooltipHolder
import com.pseudoankit.coachmark.scope.CoachMarkScope
import com.pseudoankit.coachmark.util.CoachMarkDefaults
import com.pseudoankit.coachmark.util.highlightActualView

public class DimOverlayEffect(
    private val color: Color = Color.Black.copy(alpha = .75f),
    override val overlayAnimationSpec: AnimationSpec<Float> = CoachMarkDefaults.Overlay.animationSpec
) : UnifyOverlayEffect {

    @Composable
    override fun CoachMarkScope.Overlay(
        modifier: Modifier,
        currentTooltip: TooltipHolder?,
        previousTooltip: TooltipHolder?,
        content: @Composable () -> Unit
    ) {
        val density = LocalDensity.current

        OverlayLayout(
            content,
            currentTooltip?.item,
            previousTooltip?.item,
            modifier
                .graphicsLayer(alpha = .99f)
                .drawBehind {
                    drawRect(color)
                    currentTooltip?.item?.let { tooltip ->
                        highlightActualView(tooltip, density, currentTooltip.alpha)
                    }
                    previousTooltip?.item?.let { tooltip ->
                        highlightActualView(tooltip, density, previousTooltip.alpha)
                    }
                },
        )
    }

}