package com.iheart.country.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

abstract class BaseViewModel: ViewModel() {

    private val jobDelegate = lazy { SupervisorJob() }
    private val job by jobDelegate
    protected val ioScope by lazy { CoroutineScope(job + Dispatchers.IO) }
    val error = MutableLiveData(false)
    val loading = MutableLiveData(false)






}