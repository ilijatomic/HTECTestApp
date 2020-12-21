package com.ilijatomic.htectestapp.ui.post

import com.ilijatomic.htectestapp.data.repositiry.author.AuthorRepoImpl
import com.ilijatomic.htectestapp.data.repositiry.post.PostRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostPresenter @Inject constructor(
    private val postView: PostContract.View
) : PostContract.Presenter {

    @Inject lateinit var postRepoImpl: PostRepoImpl
    @Inject lateinit var authorRepoImpl: AuthorRepoImpl

    private val compositeDisposable = CompositeDisposable()

    override fun loadPost(postId: Int) {
        val post = postRepoImpl.getPostFromDB(postId)
        if (post != null) {
            compositeDisposable.add((authorRepoImpl.getAuthor(post.userId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { postView.setLoading(true) }
                .doFinally { postView.setLoading(false) }
                .subscribe(
                    { result -> postView.onPostLoaded(post, result) },
                    { error -> postView.showError() }
                ))
        } else {
            postView.showError()
        }
    }

    override fun deletePost(postId: Int) {
        postRepoImpl.deletePostFromDb(postId)
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}