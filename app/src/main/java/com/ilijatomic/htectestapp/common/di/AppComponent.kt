package com.ilijatomic.htectestapp.common.di

import android.content.Context
import com.ilijatomic.htectestapp.data.network.ApiService
import com.ilijatomic.htectestapp.data.network.RemoteDataManager
import com.ilijatomic.htectestapp.data.storage.LocalDataManager
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class, NetworkModule::class, StorageModule::class])
interface AppComponent {

    fun getContext(): Context
    fun getApiService(): ApiService
    fun getLocalDataManager(): LocalDataManager
    fun getRemoteDataManager(): RemoteDataManager
}