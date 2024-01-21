package ch.lock.android.networkinfo.utils.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

var <T> MutableLiveData<T>.postValue: T?
    set(value) {
        postValue(value)
    }
    get() = value