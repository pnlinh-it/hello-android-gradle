package com.example.myapplication.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow

class DashboardViewModel : ViewModel() {

    sealed interface Event {
        object ShowError : Event

        data class SendLog(val count: Int) : Event
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    private val _event = MutableSharedFlow<Event>()
    val event:SharedFlow<Event>  = _event.asSharedFlow()

}