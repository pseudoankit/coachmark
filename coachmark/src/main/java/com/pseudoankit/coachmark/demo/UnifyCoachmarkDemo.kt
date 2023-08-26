package com.pseudoankit.coachmark.demo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

private enum class Keys { First, Second }

@Preview
@Composable
public fun UnifyCoachmarkDemo() {
    Box(modifier = Modifier.fillMaxSize()) {

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
            Column {
                Button(
                    onClick = {
                        show(Keys.First, Keys.Second)
                    },
                    modifier = Modifier
                        .enableCoachMark(
                            CoachMarkConfig(
                                tooltip = CoachMarkConfig.Tooltip(
                                    text = "demo1",
                                    placement = ToolTipPlacement.End,
                                    shape = ArrowToolTipShape(Arrow.Start(16.dp, 12.dp)),
                                    textColor = CoachMarkDefaults.Tooltip.textColor,
                                    bgColor = CoachMarkDefaults.Tooltip.bgColor,
                                    padding = CoachMarkDefaults.Tooltip.padding
                                ),
                                key = Keys.First
                            )
                        )
                ) {
                    Text(text = "Click me 1")
                }

                Button(
                    onClick = {
                        show(Keys.Second, Keys.First)
                    },
                    modifier = Modifier.enableCoachMark(
                        CoachMarkConfig(
                            tooltip = CoachMarkConfig.Tooltip(
                                text = "demo2",
                                placement = ToolTipPlacement.End,
                                shape = ArrowToolTipShape(Arrow.Start(16.dp, 12.dp)),
                                textColor = CoachMarkDefaults.Tooltip.textColor,
                                bgColor = CoachMarkDefaults.Tooltip.bgColor,
                                padding = CoachMarkDefaults.Tooltip.padding
                            ),
                            key = Keys.Second
                        )
                    )
                ) {
                    Text(text = "Click me 2")
                }
            }
        }
    }
}