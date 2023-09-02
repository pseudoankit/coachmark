package com.pseudoankit.coachmark.overlay

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.graphicsLayer
import com.pseudoankit.coachmark.model.TooltipConfig
import com.pseudoankit.coachmark.scope.CoachMarkScope
import com.pseudoankit.coachmark.util.buildPath

public class DimOverlayEffect(
    private val color: Color = Color.Black.copy(alpha = .75f)
) : UnifyOverlayEffect {

    @Composable
    override fun CoachMarkScope.Overlay(
        modifier: Modifier,
        content: @Composable () -> Unit
    ) {

        Box(
            modifier = modifier
                .graphicsLayer(alpha = .99f)
                .drawBehind {
                    drawRect(color)
                    highlightActualView(currentVisibleTooltip)
                }
        ) {
            content()
        }
    }

    private fun DrawScope.highlightActualView(toolTip: TooltipConfig?) {
        if (toolTip == null) return

        val path = toolTip.createPathToHighlightActualView().apply {
            translate(
                Offset(
                    x = toolTip.layout.startX,
                    y = toolTip.layout.startY,
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

    private fun TooltipConfig.createPathToHighlightActualView() = buildPath {
        addRoundRect(
            roundRect = RoundRect(
                rect = Rect(
                    offset = Offset.Zero,
                    size = Size(
                        width = layout.width.toFloat(),
                        height = layout.height.toFloat()
                    )
                )
            )
        )
    }
}