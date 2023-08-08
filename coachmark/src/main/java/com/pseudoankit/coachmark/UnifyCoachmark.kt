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
import com.pseudoankit.coachmark.internal.CoachMarkImpl
import com.pseudoankit.coachmark.model.UnifyCoachMarkConfig
import com.pseudoankit.coachmark.model.UnifyCoachMarkGlobalConfig
import com.pseudoankit.coachmark.model.UnifyCoachMarkOverlayClickEvent

@Composable
public fun <KEY> UnifyCoachmark(
    globalCoachMarkConfig: UnifyCoachMarkGlobalConfig<KEY> = UnifyCoachMarkGlobalConfig(),
    content: @Composable CoachMarkScope<KEY>.() -> Unit
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

        UnifyCoachmark<Keys>(
            globalCoachMarkConfig = UnifyCoachMarkGlobalConfig(
                itemConfig = UnifyCoachMarkGlobalConfig.ItemConfig(
                    padding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                ),
                overlayConfig = UnifyCoachMarkGlobalConfig.OverlayConfig(
                    onOverlayClicked = {
                        UnifyCoachMarkOverlayClickEvent.GoNext
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
                            UnifyCoachMarkConfig(
                                UnifyCoachMarkConfig.ItemConfig("demo1")
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
                        UnifyCoachMarkConfig(
                            UnifyCoachMarkConfig.ItemConfig("demo1")
                        )
                    )
                ) {
                    Text(text = "Click me")
                }
            }
        }
    }
}