package ch.lock.android.networkinfo.utils.extension

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import ch.lock.android.networkinfo.ui.base.viewmodel.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private const val TAG: String = "LifecycleOwnerExt"

val LifecycleOwner.lifecycleContext: Context
    get() = when (this) {
        is Activity -> this
        is Fragment -> this.context
            ?: throw NullPointerException("The context of the fragment is null.")

        else -> throw NullPointerException("This method can only use Activity or Fragment.")
    }

val LifecycleOwner.lifecycleFragmentManager: FragmentManager
    get() = when (this) {
        is AppCompatActivity -> this.supportFragmentManager
        is Fragment -> this.childFragmentManager
        else -> throw NullPointerException("This method can only use Activity or Fragment.")
    }

fun LifecycleOwner.repeatOnStarted(block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED, block)
    }
}

fun LifecycleOwner.observeBaseViewModelEvent(
    viewModel: BaseViewModel,
    isShowToast: Boolean = true
) = repeatOnStarted {
    viewModel.baseEventFlow.collect { event ->
        when (event) {
            is BaseViewModel.Event.ShowToast -> {
                if (isShowToast) Toast.makeText(
                    lifecycleContext,
                    event.text,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}