package ch.lock.android.networkinfo.ui.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.lock.android.networkinfo.utils.base.flow.EventFlow
import ch.lock.android.networkinfo.utils.base.flow.MutableEventFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private val _baseEventFlow = MutableEventFlow<Event>()
    val baseEventFlow: EventFlow<Event> = _baseEventFlow

    open fun showToast(message: String?) {
        event(Event.ShowToast(message))
    }

    private fun event(event: Event) {
        viewModelScope.launch {
            _baseEventFlow.emit(event)
        }
    }

    /**
     * ViewModel default event
     */
    sealed class Event {
        data class ShowToast(val text: String?) : Event()
    }

}