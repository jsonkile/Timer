package com.jsonkile.timer.data.repo

import com.jsonkile.timer.data.db.daos.TimerDao
import com.jsonkile.timer.data.db.entities.TimerEntity
import com.jsonkile.timer.domain.models.Timer
import com.jsonkile.timer.domain.models.toTimersList
import com.jsonkile.timer.utils.IoDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TimerRepoImpl @Inject constructor(
    private val dao: TimerDao,
    @IoDispatchers private val dispatcher: CoroutineDispatcher
) : TimerRepo {

    override suspend fun addNew() = withContext(dispatcher) {
        dao.insert(TimerEntity())
    }

    override val timers: Flow<List<Timer>> =
        dao.getTimers().map { it.toTimersList() }.flowOn(dispatcher)

    override suspend fun resume(id: Int) = withContext(dispatcher) {
        val timerEntity = checkNotNull(dao.getTimer(id))
        val totalPausedTime = if (timerEntity.stopped && timerEntity.lastStoppedAt != null)
            timerEntity.totalStopTime + (System.currentTimeMillis() - timerEntity.lastStoppedAt) else 0L
        dao.resume(id, totalPausedTime)
    }

    override suspend fun remove(id: Int) = withContext(dispatcher) {
        dao.remove(id)
    }

    override suspend fun pause(id: Int) = withContext(dispatcher) {
        dao.stop(id)
    }

    override suspend fun pauseAll() = withContext(dispatcher) {
        dao.stopAll()
    }

    override suspend fun resumeAll() = withContext(dispatcher) {
        dao.resumeAll()
    }
}