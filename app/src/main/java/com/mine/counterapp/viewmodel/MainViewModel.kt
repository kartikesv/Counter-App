package com.mine.counterapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel(){
    private var _counter = MutableStateFlow(0)
    val counter = _counter.asStateFlow()

    private val _toastMsg = MutableSharedFlow<String>()
    val toastMsg = _toastMsg.asSharedFlow()

    fun incrementCount(){
        _counter.value = _counter.value.plus(1)
    }

    fun decrementCount(){
        if(_counter.value == 0){
            viewModelScope.launch {
                _toastMsg.emit("Counter can't go below zero!")  //emit event
            }
        } else{
        _counter.value = _counter.value.minus(1)
        }
    }
}