package com.jsonkile.timer.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jsonkile.timer.presentation.models.PresentationTimer
import com.jsonkile.timer.presentation.ui.theme.TimerTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import java.util.Locale

@Composable
fun TimerComposable(
    modifier: Modifier = Modifier,
    timer: PresentationTimer,
    stopTimer: (Int) -> Unit,
    resumeTimer: (Int) -> Unit,
    removeTimer: (Int) -> Unit
) {

    val totalElapsedTime = remember(timer.stopped) {
        if (!timer.stopped) System.currentTimeMillis() - timer.startedOn - timer.totalPausedTime
        else timer.totalTimeAtLastStop
    }

    val elapsed by produceState(initialValue = totalElapsedTime, key1 = timer.stopped) {
        while (isActive && !timer.stopped) {
            delay(99L)
            value += 99L
        }
    }

    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceContainerHigh,
        shape = RoundedCornerShape(10.dp),
        onClick = { if (timer.stopped) resumeTimer(timer.id) else stopTimer(timer.id) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val minutes = elapsed / 60000
            val seconds = (elapsed % 60000) / 1000
            val milliseconds = (elapsed % 1000) / 10 //

            val formattedTime =
                String.format(Locale.getDefault(), "%02d:%02d:%02d", minutes, seconds, milliseconds)

            Text(
                text = formattedTime,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                IconButton(onClick = {
                    if (timer.stopped) resumeTimer(timer.id) else stopTimer(timer.id)
                }) {
                    Icon(
                        imageVector = if (timer.stopped) Icons.Rounded.PlayArrow else Icons.Filled.Pause,
                        contentDescription = "Play or pause",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(30.dp),
                    )
                }

                IconButton(onClick = { removeTimer(timer.id) }) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Delete button",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(26.dp),
                    )
                }
            }
        }
    }

}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewTimerComposable() {
    TimerTheme {
        TimerComposable(
            modifier = Modifier.fillMaxWidth(),
            timer = PresentationTimer(
                stopped = false,
                totalPausedTime = 3L,
                id = 0,
                startedOn = 1000L,
                totalTimeAtLastStop = 100L
            ),
            stopTimer = { }, resumeTimer = {}, removeTimer = {}
        )
    }
}