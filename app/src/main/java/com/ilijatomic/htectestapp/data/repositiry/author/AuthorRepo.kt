package com.ilijatomic.htectestapp.data.repositiry.author

import com.ilijatomic.htectestapp.data.network.model.AuthorApiModel
import io.reactivex.Observable

interface AuthorRepo {
    fun getAuthor(authorId: Int): Observable<AuthorApiModel>
}