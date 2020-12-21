package com.ilijatomic.htectestapp.ui.feed

import com.ilijatomic.htectestapp.data.storage.bean.PostBean

interface FeedContract {

    interface View {
        fun setLoading(status: Boolean)
        fun onFeedLoaded(feed: List<PostBean>)
        fun showError()
    }

    interface Presenter {
        fun loadFeed(refresh: Boolean)
        fun onDestroy()
    }
}