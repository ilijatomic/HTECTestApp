package com.ilijatomic.htectestapp.ui.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.ilijatomic.htectestapp.R
import com.ilijatomic.htectestapp.data.storage.bean.PostBean

class FeedAdapter : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    var onItemClick: ((PostBean) -> Unit)? = null

    var items: ArrayList<PostBean> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_main_feed, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = items[position].title
        holder.body.text = items[position].body
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateList(feed: ArrayList<PostBean>) {
        items = feed
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        @BindView(R.id.title) lateinit var title: TextView
        @BindView(R.id.body) lateinit var body: TextView

        init {
            ButterKnife.bind(this, view)
            view.setOnClickListener { onItemClick?.invoke(items[adapterPosition]) }
        }

    }
}