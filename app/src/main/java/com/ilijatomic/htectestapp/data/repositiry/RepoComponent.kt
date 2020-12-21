package com.ilijatomic.htectestapp.data.repositiry

import com.ilijatomic.htectestapp.common.di.AppComponent
import com.ilijatomic.htectestapp.common.di.scopes.ActivityScope
import com.ilijatomic.htectestapp.data.repositiry.author.AuthorRepoImpl
import com.ilijatomic.htectestapp.data.repositiry.post.PostRepoImpl
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class])
interface RepoComponent {

    fun getAuthorRepoImpl(): AuthorRepoImpl
    fun getPostRepoImpl(): PostRepoImpl
}