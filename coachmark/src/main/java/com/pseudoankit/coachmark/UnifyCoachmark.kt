package com.pseudoankit.coachmark

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import com.pseudoankit.coachmark.internal.toDp

data class CoachMarkItem(
    val text: String,
    val coordinates: Offset,
    val show: Boolean
)

class CoachMark<T> {
    private val items = mutableMapOf<T, CoachMarkItem>()
    var activeCoachmark by mutableStateOf<CoachMarkItem?>(null)

    fun Modifier.enableCoachMark(
        text: String,
        type: T
    ): Modifier = composed {
        onGloballyPositioned {
            val coordinates = Offset(
                x = it.positionInRoot().x,
                y = it.positionInRoot().y + it.size.height
            )

            items[type] = CoachMarkItem(text, coordinates, false)
        }
    }

    fun hide(item: T) {
        activeCoachmark = null
    }

    fun reveal(item: T) {
        activeCoachmark =
            items[item] ?: throw NotImplementedError("coachmark not enabled for the type $item")
    }
}


@Composable
fun <T> UnifyCoachmark(
    content: @Composable CoachMark<T>.() -> Unit
) {
    val coachMark = remember { CoachMark<T>() }
    val activeCoachMark = coachMark.activeCoachmark

    Box {
        content(coachMark)
        if (activeCoachMark != null) {
            Text(
                text = activeCoachMark.text,
                color = Color.White,
                modifier = Modifier
                    .offset(
                        activeCoachMark.coordinates.x.toDp(LocalDensity.current),
                        activeCoachMark.coordinates.y.toDp(LocalDensity.current)
                    )
                    .background(Color.Blue)
            )
        }
    }
}

@Preview
@Composable
fun UnifyCoachmarkDemo() {
    Box(modifier = Modifier.fillMaxSize()) {

        UnifyCoachmark<Int> {
            Column {
                Button(
                    onClick = {
                        reveal(0)
                    },
                    modifier = Modifier.enableCoachMark("text1", 0)
                ) {
                    Text(text = "Click me")
                }

                Button(
                    onClick = {
                        reveal(1)
                    },
                    modifier = Modifier.enableCoachMark("text22", 1)
                ) {
                    Text(text = "Click me")
                }
            }
        }
    }
}