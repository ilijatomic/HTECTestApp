package com.ilijatomic.htectestapp.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.snackbar.Snackbar
import com.ilijatomic.htectestapp.R
import com.ilijatomic.htectestapp.common.HTECApplication
import com.ilijatomic.htectestapp.data.storage.bean.PostBean
import com.ilijatomic.htectestapp.ui.common.BaseFragment
import com.ilijatomic.htectestapp.ui.feed.di.DaggerFeedComponent
import com.ilijatomic.htectestapp.ui.feed.di.FeedModule
import com.ilijatomic.htectestapp.ui.main.MainActivity
import com.ilijatomic.htectestapp.ui.post.PostFragment
import com.ilijatomic.htectestapp.util.NetworkUtils
import javax.inject.Inject

class FeedFragment : BaseFragment(), FeedContract.View {

    @BindView(R.id.feed_recycler_view) lateinit var recyclerView: RecyclerView
    @BindView(R.id.feed_swipe_refresh) lateinit var swipeRefreshLayout: SwipeRefreshLayout
    @BindView(R.id.feed_tv_no_data) lateinit var noDataTextView: TextView

    var feedAdapter = FeedAdapter()

    @Inject lateinit var feedPresenter: FeedPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerFeedComponent.builder()
            .appComponent(HTECApplication.instance.appComponent)
            .feedModule(FeedModule(this))
            .build()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_feed, container, false)
        ButterKnife.bind(this, view)

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = feedAdapter
        feedAdapter.onItemClick = {
            findNavController().navigate(
                R.id.action_feedFragment_to_postFragment,
                Bundle().apply {
                    putInt(PostFragment.ARG_POST_ID, it.id)
                })
        }
        swipeRefreshLayout.setOnRefreshListener {
            val isOnline = NetworkUtils.isOnline()
            if (isOnline) {
                feedPresenter.loadFeed(true)
            } else {
                showSnackBar(swipeRefreshLayout, getString(R.string.offline_mode), Snackbar.LENGTH_INDEFINITE)
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        feedPresenter.loadFeed(false)
    }

    override fun setLoading(status: Boolean) {
        swipeRefreshLayout.isRefreshing = status
    }

    override fun onFeedLoaded(feed: List<PostBean>) {
        if (feed.isNotEmpty()) {
            feedAdapter.updateList(ArrayList(feed))
            noDataTextView.visibility = View.GONE
        } else {
            noDataTextView.visibility = View.VISIBLE
        }
        hideSnackBar()
    }

    override fun showError() {
        showSnackBar(swipeRefreshLayout, getString(R.string.error_feed), Snackbar.LENGTH_LONG)
    }

    override fun onActivityDestroy() {
        feedPresenter.onDestroy()
    }
}