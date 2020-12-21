package com.ilijatomic.htectestapp.ui.post

import com.ilijatomic.htectestapp.data.network.model.AuthorApiModel
import com.ilijatomic.htectestapp.data.storage.bean.PostBean

interface PostContract {

    interface View {
        fun setLoading(status: Boolean)
        fun onPostLoaded(post: PostBean, author: AuthorApiModel)
        fun showError()
    }

    interface Presenter {
        fun loadPost(postId: Int)
        fun deletePost(postId: Int)
        fun onDestroy()
    }
}