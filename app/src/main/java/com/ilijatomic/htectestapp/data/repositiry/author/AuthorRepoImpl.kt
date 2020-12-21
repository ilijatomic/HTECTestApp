package com.ilijatomic.htectestapp.data.repositiry.author

import com.ilijatomic.htectestapp.data.network.RemoteDataManager
import com.ilijatomic.htectestapp.data.network.model.AuthorApiModel
import io.reactivex.Observable
import javax.inject.Inject

class AuthorRepoImpl @Inject constructor(
    private val remoteDataManager: RemoteDataManager,
) : AuthorRepo {

    override fun getAuthor(authorId: Int): Observable<AuthorApiModel> {
        return remoteDataManager.getAuthor(authorId)
    }
}