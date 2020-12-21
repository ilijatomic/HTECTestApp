package com.ilijatomic.htectestapp.ui.feed.di

import com.ilijatomic.htectestapp.common.di.scopes.FragmentScope
import com.ilijatomic.htectestapp.ui.feed.FeedContract
import dagger.Module
import dagger.Provides

@Module
class FeedModule(private val feedView: FeedContract.View) {

    @Provides
    @FragmentScope
    fun provideFeedView(): FeedContract.View {
        return feedView
    }
}