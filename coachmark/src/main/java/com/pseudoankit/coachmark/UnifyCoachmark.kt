package com.pseudoankit.coachmark

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.pseudoankit.coachmark.internal.CoachMarkScopeImpl
import com.pseudoankit.coachmark.internal.toDp

@Composable
fun <T> UnifyCoachmark(
    globalCoachMarkConfig: UnifyCoachMarkGlobalConfig = UnifyCoachMarkGlobalConfig(),
    content: @Composable CoachMarkScope<T>.() -> Unit
) {
    val coachMark = remember { CoachMarkScopeImpl<T>(globalCoachMarkConfig) }

    Box {
        content(coachMark)

    }
}

@Preview
@Composable
fun UnifyCoachmarkDemo() {
    Box(modifier = Modifier.fillMaxSize()) {

        UnifyCoachmark<Int> {
            Column {
//                Button(
//                    onClick = {
//                        show(0)
//                    },
//                    modifier = Modifier.enableCoachMark("text1", 0)
//                ) {
//                    Text(text = "Click me")
//                }
//
//                Button(
//                    onClick = {
//                        show(1)
//                    },
//                    modifier = Modifier.enableCoachMark("text22", 1)
//                ) {
//                    Text(text = "Click me")
//                }
            }
        }
    }
}