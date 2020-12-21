package com.ilijatomic.htectestapp

import com.ilijatomic.htectestapp.data.repositiry.post.PostRepoImpl
import com.ilijatomic.htectestapp.ui.feed.FeedContract
import com.ilijatomic.htectestapp.ui.feed.FeedPresenter
import com.ilijatomic.htectestapp.util.MockData
import com.ilijatomic.htectestapp.util.RxSchedulerRule
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FeedPresenterTest {

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    private lateinit var feedPresenter: FeedPresenter

    private val feedView: FeedContract.View = mock()
    private val postRepoImpl: PostRepoImpl = mock()

    @Before
    fun setup() {
        feedPresenter = FeedPresenter(feedView)
        feedPresenter.postRepoImpl = postRepoImpl
    }

    @Test
    fun test_showFeedWithSuccessResponse() {
        whenever(postRepoImpl.getFeed(true)).thenReturn(Observable.just(MockData.feedMock))

        feedPresenter.loadFeed(true)

        verify(feedView).setLoading(true)
        verify(feedView).setLoading(false)
        verify(feedView).onFeedLoaded(eq(MockData.feedMock))
    }

    @Test
    fun test_showFeedWithErrorResponse() {
        whenever(postRepoImpl.getFeed(true)).thenReturn(Observable.error(Throwable()))

        feedPresenter.loadFeed(true)

        verify(feedView).setLoading(true)
        verify(feedView).setLoading(false)
        verify(feedView).showError()
    }
}