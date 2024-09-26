package com.pestControlDrugs.bnet.di

import android.app.Application

class MyApplication: Application() {

    lateinit var appComponent : ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder().setContext(this).build()

    }

}