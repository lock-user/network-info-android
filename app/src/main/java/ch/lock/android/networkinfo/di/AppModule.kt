package ch.lock.android.networkinfo.di

import android.content.Context
import ch.lock.android.networkinfo.App
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModule {

    private val INSTANCE: Module = module {
        single {
            get<Context>() as App
        }
    }

    fun getModules(): List<Module> = listOf(
        INSTANCE,
        ObserverModule.INSTANCE
    )

}