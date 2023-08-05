package com.pseudoankit.coachmark

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pseudoankit.coachmark.internal.toDp

@androidx.compose.runtime.Composable
fun UnifyCoachmark(
    showCoachMark: Boolean,
    content: @Composable () -> Unit
) {
    Box {
        var coachMarkPosition by remember { mutableStateOf(Offset(0f, 0f)) }
        Box(
            modifier = Modifier
                .onGloballyPositioned {
                    val position = it.positionInRoot()
                    val size = it.size
                    coachMarkPosition = Offset(x = position.x, y = position.y + size.height)
                }
        ) {
            content()
        }

        if (showCoachMark) {
            Text(
                text = "Coachmark ..",
                modifier = Modifier
                    .offset(
                        coachMarkPosition.x.toDp(LocalDensity.current),
                        coachMarkPosition.y.toDp(LocalDensity.current)
                    )
                    .wrapContentSize()
                    .background(Color.Blue)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun UnifyCoachmarkDemo() {
    Box(modifier = Modifier.fillMaxSize()) {

        var showCoachMark by remember { mutableStateOf(false) }
        UnifyCoachmark(showCoachMark = showCoachMark) {
            Button(
                onClick = {
                    showCoachMark = true
                }
            ) {
                Text(text = "Click me")
            }
        }
    }
}