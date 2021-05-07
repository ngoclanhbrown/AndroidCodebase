package com.brown.codebase

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application(), LifecycleObserver {

    private var _isOnForeground = false
    val isOnForeground
        get() = _isOnForeground


    override fun onCreate() {
        super.onCreate()

        instance = this

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onMoveToForeground() {
        _isOnForeground = true
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun onMoveToBackground() {
        _isOnForeground = false
    }


    companion object {
        lateinit var instance: App
    }

}
