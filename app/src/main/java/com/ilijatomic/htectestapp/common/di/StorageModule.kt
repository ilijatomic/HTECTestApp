package com.ilijatomic.htectestapp.common.di

import com.ilijatomic.htectestapp.data.storage.LocalDataManager
import dagger.Module
import dagger.Provides
import io.realm.Realm
import javax.inject.Singleton

@Module
class StorageModule {

    @Provides
    @Singleton
    fun provideLocalDataManager(): LocalDataManager {
        return LocalDataManager()
    }
}