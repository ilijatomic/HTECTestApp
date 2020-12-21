package com.ilijatomic.htectestapp.ui.feed

import com.ilijatomic.htectestapp.data.repositiry.post.PostRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FeedPresenter @Inject constructor(
    private val feedView: FeedContract.View,
) : FeedContract.Presenter {

    @Inject lateinit var postRepoImpl: PostRepoImpl
    private var compositeDisposable = CompositeDisposable()

    override fun loadFeed(refresh: Boolean) {
        compositeDisposable.add((postRepoImpl.getFeed(refresh))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { feedView.setLoading(true) }
            .doFinally { feedView.setLoading(false) }
            .subscribe(
                { result -> feedView.onFeedLoaded(result) },
                { error -> feedView.showError() }
            ))
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}