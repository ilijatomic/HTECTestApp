package com.ilijatomic.htectestapp.ui.post

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.snackbar.Snackbar
import com.ilijatomic.htectestapp.R
import com.ilijatomic.htectestapp.common.HTECApplication
import com.ilijatomic.htectestapp.data.network.model.AuthorApiModel
import com.ilijatomic.htectestapp.data.storage.bean.PostBean
import com.ilijatomic.htectestapp.ui.common.BaseFragment
import com.ilijatomic.htectestapp.ui.main.MainActivity
import com.ilijatomic.htectestapp.ui.post.di.DaggerPostComponent
import com.ilijatomic.htectestapp.ui.post.di.PostModule
import com.ilijatomic.htectestapp.util.NetworkUtils
import javax.inject.Inject

class PostFragment : BaseFragment(), PostContract.View {

    companion object {
        const val ARG_POST_ID = "post_id"
    }

    @BindView(R.id.post_info) lateinit var postInfo: LinearLayout
    @BindView(R.id.post_tv_title) lateinit var titleTextView: TextView
    @BindView(R.id.post_tv_body) lateinit var bodyTextView: TextView
    @BindView(R.id.post_tv_author_name) lateinit var authorTextView: TextView
    @BindView(R.id.post_tv_author_email) lateinit var emailTextView: TextView
    @BindView(R.id.post_progress_bar) lateinit var progressbar: ProgressBar

    private var postId = 0

    @Inject lateinit var postPresenter: PostPresenter
    private lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerPostComponent.builder()
            .appComponent(HTECApplication.instance.appComponent)
            .postModule(PostModule(this))
            .build()
            .inject(this)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_post, container, false)
        ButterKnife.bind(this, view)
        postId = arguments?.getInt(ARG_POST_ID) ?: -1

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val isOnline = NetworkUtils.isOnline()
        if (isOnline) {
            postPresenter.loadPost(postId)
        } else {
            showSnackBar(progressbar, getString(R.string.offline_mode), Snackbar.LENGTH_INDEFINITE)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.post_fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete -> postPresenter.deletePost(postId)
        }

        mainActivity.onBackPressed()
        return true
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mainActivity = activity as MainActivity
    }

    override fun setLoading(status: Boolean) {
        if (status) {
            progressbar.visibility = View.VISIBLE
            postInfo.visibility = View.GONE
        } else {
            progressbar.visibility = View.GONE
            postInfo.visibility = View.VISIBLE
        }
    }

    override fun onPostLoaded(post: PostBean, author: AuthorApiModel) {
        titleTextView.text = post.title
        bodyTextView.text = post.body
        authorTextView.text = author.name
        emailTextView.text = author.email
    }

    override fun showError() {
        postInfo.visibility = View.GONE
        showSnackBar(titleTextView, getString(R.string.error_post), Snackbar.LENGTH_LONG)
    }

    override fun onActivityDestroy() {
        postPresenter.onDestroy()
    }
}