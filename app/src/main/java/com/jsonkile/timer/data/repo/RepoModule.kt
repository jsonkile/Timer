package com.jsonkile.timer.data.repo

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepoModule {

    @Binds
    abstract fun bindTimerRepo(timerRepoImpl: TimerRepoImpl): TimerRepo
}