package com.jsonkile.timer.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "timers")
data class TimerEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "started_on")
    val startedOn: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "total_stop_time")
    val totalStopTime: Long = 0L,
    val stopped: Boolean = false,
    @ColumnInfo(name = "last_stopped_at")
    val lastStoppedAt: Long? = null,
)

