package com.jsonkile.timer.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jsonkile.timer.data.db.daos.TimerDao
import com.jsonkile.timer.data.db.entities.TimerEntity

@Database(entities = [TimerEntity::class], version = 1)
abstract class TimerRoomDatabase : RoomDatabase() {
    abstract fun timersDao(): TimerDao
}