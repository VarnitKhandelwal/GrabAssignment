package com.android.varnit.grabassignment.ui.news

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.android.varnit.grabassignment.models.ArticleModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Created by varnit.khandelwal on 28/04/2019.
 */
class NewsAdapter(
    private val context: Context, private val list: MutableList<ArticleModel>,
    activity: Activity
) : RecyclerView.Adapter<NewsAdapter.ListViewHolder>() {

    private val listener: onItemClickListener

    init {
        this.listener = activity as onItemClickListener
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView = LayoutInflater.from(context)
            .inflate(com.android.varnit.grabassignment.R.layout.item_layout, parent, false)
        return ListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var articleModel = list[position]

        val options = RequestOptions()
            .placeholder(com.android.varnit.grabassignment.R.drawable.image_placeholder)

        holder.news!!.text = articleModel.title
        Glide.with(holder.itemView.context)
            .load(articleModel.urlToImage)
            .apply(options).into(holder.image)

        holder.layout!!.setOnClickListener {
            listener.itemDetail(newsUrl = articleModel.url)
        }
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var layout =
            itemView.findViewById<RelativeLayout>(com.android.varnit.grabassignment.R.id.news_layout)
        val image =
            itemView.findViewById<ImageView>(com.android.varnit.grabassignment.R.id.item_image)
        val news = itemView.findViewById<TextView>(com.android.varnit.grabassignment.R.id.news_text)
    }

    interface onItemClickListener {
        fun itemDetail(newsUrl: String)
    }
}