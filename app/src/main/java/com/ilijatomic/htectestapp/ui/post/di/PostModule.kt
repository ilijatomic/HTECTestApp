package com.ilijatomic.htectestapp.ui.post.di

import com.ilijatomic.htectestapp.common.di.scopes.FragmentScope
import com.ilijatomic.htectestapp.ui.post.PostContract
import dagger.Module
import dagger.Provides

@Module
class PostModule(private val postView: PostContract.View) {

    @Provides
    @FragmentScope
    fun providePostView(): PostContract.View {
        return postView
    }
}