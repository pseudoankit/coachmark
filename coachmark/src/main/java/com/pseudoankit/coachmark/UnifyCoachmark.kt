package com.pseudoankit.coachmark

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pseudoankit.coachmark.model.CoachMarkConfig
import com.pseudoankit.coachmark.model.CoachMarkGlobalConfig
import com.pseudoankit.coachmark.model.CoachMarkOverlayClickEvent
import com.pseudoankit.coachmark.scope.CoachMarkScope
import com.pseudoankit.coachmark.ui.CoachMarkImpl

@Composable
public fun UnifyCoachmark(
    globalCoachMarkConfig: CoachMarkGlobalConfig = CoachMarkGlobalConfig(),
    content: @Composable CoachMarkScope.() -> Unit
) {
    CoachMarkImpl(
        globalCoachMarkConfig = globalCoachMarkConfig,
        content = content
    )
}

private enum class Keys { First, Second }

@Preview
@Composable
public fun UnifyCoachmarkDemo() {
    Box(modifier = Modifier.fillMaxSize()) {

        UnifyCoachmark(
            globalCoachMarkConfig = CoachMarkGlobalConfig(
                itemConfig = CoachMarkGlobalConfig.ItemConfig(
                    padding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                ),
                overlayConfig = CoachMarkGlobalConfig.OverlayConfig(
                    onOverlayClicked = {
                        CoachMarkOverlayClickEvent.GoNext
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
                            Keys.First,
                            CoachMarkConfig(
                                CoachMarkConfig.ItemConfig("demo1")
                            )
                        )
                ) {
                    Text(text = "Click me")
                }

                Button(
                    onClick = {
                        show(Keys.Second)
                    },
                    modifier = Modifier.enableCoachMark(
                        Keys.Second,
                        CoachMarkConfig(
                            CoachMarkConfig.ItemConfig("demo1")
                        )
                    )
                ) {
                    Text(text = "Click me")
                }
            }
        }
    }
}