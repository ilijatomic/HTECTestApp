package com.ilijatomic.htectestapp.data.storage.bean

import com.ilijatomic.htectestapp.data.network.model.PostApiModel
import com.ilijatomic.htectestapp.util.Const
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class PostBean(
    @PrimaryKey var id: Int = -1,
    var userId: Int = -1,
    var title: String = Const.STRING_EMPTY,
    var body: String = Const.STRING_EMPTY
) : RealmObject() {
    constructor(postApiModel: PostApiModel) : this() {
        userId = postApiModel.userId
        id = postApiModel.id
        title = postApiModel.title
        body = postApiModel.body
    }
}