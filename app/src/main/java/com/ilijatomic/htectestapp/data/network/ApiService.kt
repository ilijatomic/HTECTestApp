package com.ilijatomic.htectestapp.data.network

import com.ilijatomic.htectestapp.data.network.model.AuthorApiModel
import com.ilijatomic.htectestapp.data.network.model.PostApiModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/posts")
    fun getFeed(): Observable<List<PostApiModel>>

    @GET("/users/{userId}")
    fun getAuthor(@Path("userId") authorId: Int): Observable<AuthorApiModel>
}