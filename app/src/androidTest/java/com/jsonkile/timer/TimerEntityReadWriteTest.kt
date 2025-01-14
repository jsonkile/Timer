package com.jsonkile.timer

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jsonkile.timer.data.db.TimerRoomDatabase
import com.jsonkile.timer.data.db.daos.TimerDao
import com.jsonkile.timer.data.db.entities.TimerEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TimerEntityReadWriteTest {
    private lateinit var db: TimerRoomDatabase
    private lateinit var timerDao: TimerDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, TimerRoomDatabase::class.java).build()
        timerDao = db.timersDao()
    }

    @After
    fun finish() {
        db.close()
    }

    @Test
    fun addTimerAndReadTimers() = runTest {
        db.timersDao().insert(TimerEntity(startedOn = 1L))
        db.timersDao().insert(TimerEntity(startedOn = 2L))
        db.timersDao().insert(TimerEntity(startedOn = 3L))

        val timers = db.timersDao().getTimers().first()
        assertEquals(3, timers.size)

        assertEquals(3L, timers.first().startedOn)
    }
}