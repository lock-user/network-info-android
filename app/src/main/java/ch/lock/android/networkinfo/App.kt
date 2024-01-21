package ch.lock.android.networkinfo

import android.app.Application
import ch.lock.android.networkinfo.di.AppModule
import logcat.AndroidLogcatLogger
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        initLogger()
    }

    /**
     * 코인 초기화
     */
    private fun initKoin() = startKoin {
        androidLogger()
        androidContext(this@App)
        koin.loadModules(AppModule.getModules())
    }

    /**
     * 로거 초기화
     */
    private fun initLogger() {
        AndroidLogcatLogger.installOnDebuggableApp(this)
    }

}