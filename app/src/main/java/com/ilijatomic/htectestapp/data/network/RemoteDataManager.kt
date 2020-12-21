package com.ilijatomic.htectestapp.data.network

import com.ilijatomic.htectestapp.data.network.model.AuthorApiModel
import com.ilijatomic.htectestapp.data.storage.bean.PostBean
import io.reactivex.Observable
import javax.inject.Inject

class RemoteDataManager @Inject constructor(
    private val apiService: ApiService
) {

    fun getFeed(): Observable<List<PostBean>> {
        return apiService.getFeed()
            .doOnError { error(Throwable()) }
            .flatMap {
                if (it.isEmpty()) {
                    Observable.error(Throwable())
                } else {
                    val feed = ArrayList<PostBean>()
                    for (post in it) {
                        feed.add(PostBean(post))
                    }
                    Observable.just(feed)
                }
            }
    }

    fun getAuthor(authorId: Int): Observable<AuthorApiModel> {
        return apiService.getAuthor(authorId)
            .doOnError { error(Throwable()) }
            .flatMap {
                if (it.name.isEmpty()) {
                    Observable.error(Throwable())
                } else {
                    Observable.just(it)
                }
            }
    }
}