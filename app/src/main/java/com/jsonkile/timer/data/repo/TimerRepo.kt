package com.jsonkile.timer.data.repo

import com.jsonkile.timer.domain.models.Timer
import kotlinx.coroutines.flow.Flow

interface TimerRepo {

    suspend fun addNew()

    val timers: Flow<List<Timer>>

    suspend fun pause(id: Int)

    suspend fun resume(id: Int)

    suspend fun remove(id: Int)

    suspend fun pauseAll()

    suspend fun resumeAll()
}