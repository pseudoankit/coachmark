package com.pseudoankit.coachmark.demo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pseudoankit.coachmark.CoachMarkDefaults
import com.pseudoankit.coachmark.UnifyCoachmark
import com.pseudoankit.coachmark.model.CoachMarkConfig
import com.pseudoankit.coachmark.model.CoachMarkGlobalConfig
import com.pseudoankit.coachmark.model.OverlayClickEvent
import com.pseudoankit.coachmark.model.ToolTipPlacement
import com.pseudoankit.coachmark.shape.Arrow
import com.pseudoankit.coachmark.shape.ArrowToolTipShape

private enum class Keys { Start, End, Top, Bottom }

@Preview
@Composable
public fun UnifyCoachmarkDemo() {
    Box(modifier = Modifier) {

        UnifyCoachmark(
            config = CoachMarkGlobalConfig(
                tooltip = CoachMarkGlobalConfig.Tooltip(),
                overlay = CoachMarkGlobalConfig.Overlay(
                    onClick = {
                        OverlayClickEvent.GoNext
                    }
                )
            )
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                ToolTipPlacement.values().forEach {
                    if (it == ToolTipPlacement.Top) {
                        Spacer(modifier = Modifier.height(100.dp))
                    }
                    Button(
                        onClick = {
                            show(it)
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .enableCoachMark(
                                CoachMarkConfig(
                                    tooltip = CoachMarkConfig.Tooltip(
                                        text = it.name,
                                        placement = it,
                                        shape = ArrowToolTipShape(
                                            when (it) {
                                                ToolTipPlacement.Start -> Arrow.Start(16.dp, 12.dp)
                                                ToolTipPlacement.End -> Arrow.End(16.dp, 12.dp)
                                                ToolTipPlacement.Top -> Arrow.Top(16.dp, 12.dp)
                                                ToolTipPlacement.Bottom -> Arrow.Bottom(
                                                    16.dp,
                                                    12.dp
                                                )
                                            }
                                        ),
                                        textColor = CoachMarkDefaults.Tooltip.textColor,
                                        bgColor = CoachMarkDefaults.Tooltip.bgColor,
                                        padding = CoachMarkDefaults.Tooltip.padding
                                    ),
                                    key = it
                                )
                            )
                    ) {
                        Text(text = it.name)
                    }
                }
            }
        }
    }
}