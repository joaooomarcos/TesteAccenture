package br.com.accenture.projetoaurora

import android.app.Application
import android.content.Context
import timber.log.Timber


class App: Application(){

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object {

        var instance: App? = null

        private operator fun get(context: Context): App {
            return context.applicationContext as App
        }
    }
}