package com.jsonkile.timer.data.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesRoomDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, TimerRoomDatabase::class.java, "timer-database").build()

    @Provides
    @Singleton
    fun providesTimerDao(db: TimerRoomDatabase) = db.timersDao()
}