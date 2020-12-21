package com.ilijatomic.htectestapp.data.repositiry.post

import com.ilijatomic.htectestapp.data.storage.bean.PostBean
import io.reactivex.Observable

interface PostRepo {

    fun getFeed(refresh: Boolean): Observable<List<PostBean>>
    fun getPostFromDB(postId: Int): PostBean?
    fun deletePostFromDb(postId: Int)
}