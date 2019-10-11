package com.amin.sample.base

import android.app.Application
import android.content.res.Resources

class App : Application(){

    override fun onCreate() {
        super.onCreate()

        app = this
        res = resources
    }

    companion object {

        lateinit var app: App
        lateinit var res: Resources
    }
}