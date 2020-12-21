package com.ilijatomic.htectestapp.ui.post.di

import com.ilijatomic.htectestapp.common.di.AppComponent
import com.ilijatomic.htectestapp.common.di.scopes.FragmentScope
import com.ilijatomic.htectestapp.ui.post.PostFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [AppComponent::class], modules = [PostModule::class])
interface PostComponent {

    fun inject(postFragment: PostFragment)
}