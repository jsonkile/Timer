package com.jsonkile.timer.presentation

import android.content.res.Configuration
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jsonkile.timer.presentation.models.PresentationTimer
import com.jsonkile.timer.presentation.ui.theme.TimerTheme

@Composable
fun LandingScreen(
    modifier: Modifier,
    timers: List<PresentationTimer> = emptyList(),
    stopTimer: (Int) -> Unit,
    resumeTimer: (Int) -> Unit,
    removeTimer: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 25.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(items = timers, key = { it.id }) { timer ->
            TimerComposable(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateItem(fadeOutSpec = tween(durationMillis = 200)),
                timer = timer,
                stopTimer = stopTimer,
                resumeTimer = resumeTimer,
                removeTimer = removeTimer
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewLandingScreen() {
    TimerTheme {
        LandingScreen(
            modifier = Modifier.fillMaxSize(),
            timers = listOf(
                PresentationTimer(
                    totalPausedTime = 2000L,
                    id = 0,
                    startedOn = 1000L,
                    totalTimeAtLastStop = 100L
                ),
                PresentationTimer(
                    totalPausedTime = 5000L,
                    id = 0,
                    startedOn = 1000L,
                    totalTimeAtLastStop = 100L
                ),
                PresentationTimer(
                    totalPausedTime = 10000L,
                    id = 0,
                    startedOn = 1000L,
                    totalTimeAtLastStop = 100L
                )
            ),
            stopTimer = {},
            resumeTimer = {},
            removeTimer = {}
        )
    }
}

@Composable
fun LandingScreenContainer(
    modifier: Modifier,
    landingScreenViewModel: LandingScreenViewModel
) {
    val timers = landingScreenViewModel.timers.collectAsState().value

    LandingScreen(
        modifier = modifier,
        stopTimer = landingScreenViewModel::stopTimer,
        resumeTimer = landingScreenViewModel::resumeTimer,
        removeTimer = landingScreenViewModel::removeTimer,
        timers = timers
    )
}