package com.ilijatomic.htectestapp.data.storage

import com.ilijatomic.htectestapp.data.storage.bean.ApplicationBean
import com.ilijatomic.htectestapp.data.storage.bean.PostBean
import io.reactivex.Observable
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import javax.inject.Inject

class LocalDataManager @Inject constructor() {

    lateinit var realm: Realm

    fun savePosts(posts: List<PostBean>) {
        if (posts.isEmpty()) {
            return
        }
        realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        realm.insert(posts)
        realm.commitTransaction()
        realm.close()
    }

    fun getPosts(): Observable<List<PostBean>> {
        realm = Realm.getDefaultInstance()
        val result = realm.copyFromRealm(realm.where<PostBean>().findAll())
        realm.close()
        return Observable.just(result)
    }

    fun getLastUpdated(): ApplicationBean? {
        realm = Realm.getDefaultInstance()
        val result = realm.where<ApplicationBean>().findFirst()
        val applicationBean = if (result != null) realm.copyFromRealm(result) else null
        realm.close()
        return applicationBean
    }

    fun saveLastUpdated() {
        realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        val applicationBean = realm.createObject<ApplicationBean>()
        applicationBean.lastUpdated = System.currentTimeMillis()
        realm.insertOrUpdate(applicationBean)
        realm.commitTransaction()
        realm.close()
    }

    fun deleteAll() {
        realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        realm.deleteAll()
        realm.commitTransaction()
        realm.close()
    }

    fun deletePost(postId: Int) {
        if (postId == -1) {
            return
        }
        realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        realm.where<PostBean>().equalTo("id", postId).findFirst()?.deleteFromRealm()
        realm.commitTransaction()
        realm.close()
    }

    fun getPostsById(postId: Int): PostBean? {
        realm = Realm.getDefaultInstance()
        val result = realm.where<PostBean>().equalTo("id", postId).findFirst()
        val postBean = if (result != null) realm.copyFromRealm(result) else null
        realm.close()
        return postBean
    }

}