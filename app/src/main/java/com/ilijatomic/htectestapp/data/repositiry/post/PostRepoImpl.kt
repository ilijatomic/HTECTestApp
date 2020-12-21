package com.ilijatomic.htectestapp.data.repositiry.post

import com.ilijatomic.htectestapp.data.network.RemoteDataManager
import com.ilijatomic.htectestapp.data.storage.LocalDataManager
import com.ilijatomic.htectestapp.data.storage.bean.PostBean
import com.ilijatomic.htectestapp.util.Const
import io.reactivex.Observable
import javax.inject.Inject

class PostRepoImpl @Inject constructor(
    private val remoteDataManager: RemoteDataManager,
    private val localDataManager: LocalDataManager
) : PostRepo {

    override fun getFeed(refresh: Boolean): Observable<List<PostBean>> {
        val applicationBean = localDataManager.getLastUpdated()
        var update = false
        if (applicationBean != null) {
            val currentTime = System.currentTimeMillis()
            if (currentTime - applicationBean.lastUpdated > Const.TIME_5_MINUTES_IN_MILLISECONDS) {
                update = true
            }
        } else {
            update = true
        }

        return if (refresh || update) {
            remoteDataManager.getFeed()
                .doOnNext {
                    localDataManager.deleteAll()
                    localDataManager.savePosts(it)
                    localDataManager.saveLastUpdated()
                }
        } else {
            localDataManager.getPosts()
        }
    }

    override fun getPostFromDB(postId: Int): PostBean? {
        return localDataManager.getPostsById(postId)
    }

    override fun deletePostFromDb(postId: Int) {
        localDataManager.deletePost(postId)
    }
}