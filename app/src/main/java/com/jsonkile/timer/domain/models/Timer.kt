package com.jsonkile.timer.domain.models

import com.jsonkile.timer.data.db.entities.TimerEntity
import com.jsonkile.timer.presentation.models.PresentationTimer


data class Timer(
    val id: Int = 0,
    val startedOn: Long,
    val totalPausedTime: Long,
    val stopped: Boolean,
    val lastStoppedAt: Long?,
)

fun TimerEntity.toTimer() =
    Timer(
        id = id,
        startedOn = startedOn,
        stopped = stopped,
        totalPausedTime = totalStopTime,
        lastStoppedAt = lastStoppedAt
    )

fun List<TimerEntity>.toTimersList() =
    this.map { it.toTimer() }

fun List<Timer>.toPresentationTimers() =
    this.map {
        PresentationTimer(
            startedOn = it.startedOn,
            stopped = it.stopped,
            id = it.id,
            totalPausedTime = it.totalPausedTime,
            totalTimeAtLastStop = (it.lastStoppedAt ?: 0) - it.startedOn - it.totalPausedTime
        )
    }