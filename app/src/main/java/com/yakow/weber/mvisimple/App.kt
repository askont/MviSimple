package com.yakow.weber.mvisimple

import android.app.Application
import timber.log.Timber

/**
 * Created on 29.05.19
 * @author YWeber */

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }


}