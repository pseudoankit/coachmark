package com.pseudoankit.coachmark

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pseudoankit.coachmark.internal.CoachMarkImpl
import com.pseudoankit.coachmark.internal.CoachMarkScopeImpl
import com.pseudoankit.coachmark.internal.toDp

@Composable
public fun <T> UnifyCoachmark(
    globalCoachMarkConfig: UnifyCoachMarkGlobalConfig = UnifyCoachMarkGlobalConfig(),
    content: @Composable CoachMarkScope<T>.() -> Unit
) {
    CoachMarkImpl(
        globalCoachMarkConfig = globalCoachMarkConfig,
        content = content
    )
}

@Preview
@Composable
public fun UnifyCoachmarkDemo() {
    Box(modifier = Modifier.fillMaxSize()) {

        UnifyCoachmark<Int>(
            globalCoachMarkConfig = UnifyCoachMarkGlobalConfig(
                itemConfig = UnifyCoachMarkGlobalConfig.ItemConfig(
                    padding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                )
            )
        ) {
            Column {
                Button(
                    onClick = {
                        show(0)
                    },
                    modifier = Modifier
                        .enableCoachMark(
                            0,
                            UnifyCoachMarkConfig(
                                UnifyCoachMarkConfig.ItemConfig("demo1")
                            )
                        )
                ) {
                    Text(text = "Click me")
                }

                Button(
                    onClick = {
                        show(1)
                    },
                    modifier = Modifier.enableCoachMark(
                        1,
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