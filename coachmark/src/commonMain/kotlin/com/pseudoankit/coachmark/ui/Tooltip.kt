package com.pseudoankit.coachmark.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import com.pseudoankit.coachmark.model.TooltipHolder
import com.pseudoankit.coachmark.util.CoachMarkKey

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
    modifier: Modifier = Modifier,
    content: @Composable (CoachMarkKey) -> Unit
) {
    tooltipHolder?.item?.let { activeItem ->
        Box(modifier = modifier.alpha(tooltipHolder.alpha)) {
            content(activeItem.key)
        }
    }
}
