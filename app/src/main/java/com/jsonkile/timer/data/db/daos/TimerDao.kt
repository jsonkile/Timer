package com.jsonkile.timer.data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jsonkile.timer.data.db.entities.TimerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TimerDao {
    @Query("SELECT * FROM timers ORDER BY started_on DESC")
    fun getTimers(): Flow<List<TimerEntity>>

    @Query("SELECT * FROM timers WHERE id = :id")
    fun getTimer(id: Int): TimerEntity?

    @Insert
    fun insert(timerEntity: TimerEntity)

    @Query("UPDATE timers SET stopped = 1")
    fun stopAll()

    @Query("UPDATE timers SET stopped = 0")
    fun resumeAll()

    @Query("UPDATE timers SET stopped = 0, total_stop_time = :totalPausedTime WHERE id = :id")
    fun resume(id: Int, totalPausedTime: Long)

    @Query("UPDATE timers SET stopped = 1, last_stopped_at = :lastStoppedAt WHERE id = :id")
    fun stop(id: Int, lastStoppedAt: Long = System.currentTimeMillis())

    @Query("DELETE FROM timers WHERE id = :id")
    fun remove(id: Int)
}