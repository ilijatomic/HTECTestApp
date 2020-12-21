package com.ilijatomic.htectestapp.common

import android.app.Application
import com.ilijatomic.htectestapp.common.di.AppComponent
import com.ilijatomic.htectestapp.common.di.ContextModule
import com.ilijatomic.htectestapp.common.di.DaggerAppComponent
import io.realm.Realm

class HTECApplication : Application() {

    companion object {
        lateinit var instance: HTECApplication private set
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        instance = this

        appComponent = DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()
    }

}