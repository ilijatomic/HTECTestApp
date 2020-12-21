package com.ilijatomic.htectestapp.ui.feed.di

import com.ilijatomic.htectestapp.common.di.AppComponent
import com.ilijatomic.htectestapp.common.di.scopes.FragmentScope
import com.ilijatomic.htectestapp.ui.feed.FeedFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [AppComponent::class], modules = [FeedModule::class])
interface FeedComponent {

    fun inject(feedFragment: FeedFragment)
}