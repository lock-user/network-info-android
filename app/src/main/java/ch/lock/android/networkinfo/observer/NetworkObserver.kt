package ch.lock.android.networkinfo.observer

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ch.lock.android.networkinfo.utils.extension.postValue
import logcat.logcat

class NetworkObserver(
    applicationContext: Context
) : ConnectivityManager.NetworkCallback() {

    private val instance: ConnectivityManager by lazy {
        applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    private val networkRequest: NetworkRequest by lazy {
        NetworkRequest.Builder().build()
    }

    private val _isAvailable: MutableLiveData<Boolean> = MutableLiveData()
    val isAvailable: LiveData<Boolean> = _isAvailable

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        logcat { "onAvailable." }
        _isAvailable.postValue = true
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        logcat { "onLost." }
        _isAvailable.postValue = false
    }

    fun register() {
        logcat { "register." }
        instance.registerNetworkCallback(networkRequest, this)
    }

    fun unregister() {
        logcat { "unregister." }
        instance.unregisterNetworkCallback(this)
    }

}