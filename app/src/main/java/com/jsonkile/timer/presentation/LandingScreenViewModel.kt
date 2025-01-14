package com.jsonkile.timer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsonkile.timer.data.repo.TimerRepo
import com.jsonkile.timer.domain.models.toPresentationTimers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LandingScreenViewModel @Inject constructor(private val timerRepo: TimerRepo) : ViewModel() {

    val timers = timerRepo.timers
        .map { it.toPresentationTimers() }
        .flowOn(Dispatchers.IO)
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun stopTimer(id: Int) {
        viewModelScope.launch {
            timerRepo.pause(id)
        }
    }

    fun resumeTimer(id: Int) {
        viewModelScope.launch {
            timerRepo.resume(id)
        }
    }

    fun addNewTimer() {
        viewModelScope.launch {
            timerRepo.addNew()
        }
    }

    fun removeTimer(id: Int) {
        viewModelScope.launch {
            timerRepo.remove(id)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.launch {
            timerRepo.pauseAll()
        }
    }
}