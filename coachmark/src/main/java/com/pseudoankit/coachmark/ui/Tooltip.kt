package com.pseudoankit.coachmark.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import com.pseudoankit.coachmark.model.ToolTipPlacement
import com.pseudoankit.coachmark.model.TooltipConfig
import com.pseudoankit.coachmark.model.TooltipHolder
import com.pseudoankit.coachmark.util.CoachMarkKey
import com.pseudoankit.coachmark.util.rememberMutableStateOf
import com.pseudoankit.coachmark.util.toDp

/**
 * composable to render the tooltip
 * responsible to place and animate the tooltip
 *
 * @param tooltipHolder contains the tooltip to be shown and alpha to be applied
 * @param content content to be rendered when showing, to be passed by client
 */
@Composable
internal fun Tooltip(
    tooltipHolder: TooltipHolder?,
    content: @Composable (CoachMarkKey) -> Unit
) {
    val density = LocalDensity.current
    var toolTipSize by rememberMutableStateOf(value = IntSize(0, 0))

    tooltipHolder?.item?.let { activeItem ->
        Box(
            modifier = Modifier
                .onGloballyPositioned {
                    toolTipSize = it.size
                }
                .offset(
                    x = activeItem.offsetX(density, toolTipSize),
                    y = activeItem.offsetY(density, toolTipSize),
                )
                .alpha(tooltipHolder.alpha)
        ) {
            content(activeItem.key)
        }
    }
}

private fun TooltipConfig.offsetX(
    density: Density, toolTipSize: IntSize
) = when (toolTipPlacement) {
    ToolTipPlacement.Start -> layout.startX - toolTipSize.width
    ToolTipPlacement.End -> layout.endX
    ToolTipPlacement.Top, ToolTipPlacement.Bottom -> {
        val viewCenter = (layout.startX + layout.endX).div(2)
        val toolTipCenter = (toolTipSize.width).div(2)
        viewCenter - toolTipCenter
    }
}.toDp(density)

private fun TooltipConfig.offsetY(
    density: Density, toolTipSize: IntSize
) = when (toolTipPlacement) {
    ToolTipPlacement.Start, ToolTipPlacement.End -> {
        val viewCenter = (layout.startY + layout.endY).div(2)
        val toolTipCenter = toolTipSize.height.div(2)
        viewCenter - toolTipCenter
    }

    ToolTipPlacement.Top -> layout.startY - toolTipSize.height
    ToolTipPlacement.Bottom -> layout.endY
}.toDp(density)