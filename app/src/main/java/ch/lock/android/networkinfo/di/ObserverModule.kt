package ch.lock.android.networkinfo.di

import ch.lock.android.networkinfo.observer.NetworkObserver
import org.koin.core.module.Module
import org.koin.dsl.module

object ObserverModule {

    val INSTANCE: Module = module {
        single {
            NetworkObserver(
                get()
            )
        }
    }

}