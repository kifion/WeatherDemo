package com.example.weatherapp.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<T>(protected val uiContext: CoroutineContext) : ViewModel(), CoroutineScope {
    protected var jobTracker: Job

    init {
        jobTracker = Job()
    }

    protected val errorState = MutableLiveData<String>()
    val error: LiveData<String> get() = errorState

    protected val loadingState = MutableLiveData<Unit>()
    val loading: LiveData<Unit> get() = loadingState

    override val coroutineContext: CoroutineContext
        get() = uiContext + jobTracker
}