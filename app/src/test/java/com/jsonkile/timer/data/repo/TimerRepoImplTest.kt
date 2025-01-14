package com.jsonkile.timer.data.repo

import com.jsonkile.timer.data.db.daos.TimerDao
import com.jsonkile.timer.data.db.entities.TimerEntity
import com.jsonkile.timer.domain.models.Timer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

class TimerRepoImplTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testAddNewTimerAndGetTimers() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)

        val dao = object : TimerDao {
            private val holder = mutableListOf<TimerEntity>()

            override fun getTimers(): Flow<List<TimerEntity>> {
                return flow { emit(holder) }
            }

            override fun insert(timerEntity: TimerEntity) {
                holder.add(timerEntity)
            }
        }

        val timerRepo = TimerRepoImpl(dispatcher = dispatcher, dao = dao)
        timerRepo.addNew(Timer(totalPausedTime = 2L, stopped = true))
        timerRepo.addNew(Timer(totalPausedTime = 4L, stopped = false))

        advanceUntilIdle()

        assertEquals(2, timerRepo.getAll().first().size)
    }
}