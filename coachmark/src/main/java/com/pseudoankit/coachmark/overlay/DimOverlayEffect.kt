package com.pseudoankit.coachmark.overlay

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import com.pseudoankit.coachmark.model.TooltipConfig
import com.pseudoankit.coachmark.model.pathToHighlight
import com.pseudoankit.coachmark.scope.CoachMarkScope

public class DimOverlayEffect(
    private val color: Color = Color.Black.copy(alpha = .75f)
) : UnifyOverlayEffect {

    @Composable
    override fun CoachMarkScope.Overlay(
        modifier: Modifier,
        content: @Composable () -> Unit
    ) {

        val density = LocalDensity.current
        val layoutDirection = LocalLayoutDirection.current

        Box(
            modifier = modifier
                .graphicsLayer(alpha = .99f)
                .drawBehind {
                    drawRect(color)
                    highlightActualView(currentVisibleTooltip, density, layoutDirection)
                }
        ) {
            content()
        }
    }

    private fun DrawScope.highlightActualView(
        toolTip: TooltipConfig?,
        density: Density,
        layoutDirection: LayoutDirection
    ) {
        if (toolTip == null) return

        val startPadding =
            toolTip.highlightedViewConfig.padding.calculateStartPadding(layoutDirection).toPx()
        val topPadding =
            toolTip.highlightedViewConfig.padding.calculateTopPadding().toPx()


        val path = toolTip.highlightedViewConfig.shape.pathToHighlight(
            density = density,
            size = Size(
                width = run {
                    val width = toolTip.layout.width.toFloat()
                    val endPadding =
                        toolTip.highlightedViewConfig.padding.calculateEndPadding(layoutDirection)
                            .toPx()
                    width + startPadding + endPadding
                },
                height = run {
                    val height = toolTip.layout.height.toFloat()
                    val bottomPadding =
                        toolTip.highlightedViewConfig.padding.calculateBottomPadding().toPx()
                    height + topPadding + bottomPadding
                },
            )
        ).apply {
            translate(
                Offset(
                    x = toolTip.layout.startX - startPadding,
                    y = toolTip.layout.startY - topPadding,
                )
            )
        }

        drawPath(
            path = path,
            color = Color.Black,
            alpha = 1f,
            blendMode = BlendMode.DstOut,
        )
    }
}