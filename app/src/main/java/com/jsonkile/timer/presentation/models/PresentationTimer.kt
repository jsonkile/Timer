package com.jsonkile.timer.presentation.models

data class PresentationTimer(
    val startedOn: Long,
    val stopped: Boolean = true,
    val totalPausedTime: Long,
    val totalTimeAtLastStop: Long,
    val id: Int
)
