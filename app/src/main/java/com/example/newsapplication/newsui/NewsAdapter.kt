package com.example.newsapplication.newsui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.R
import com.example.newsapplication.apicall.NewsProperty
import com.example.newsapplication.databinding.NewsItemBinding

class NewsAdapter(val clickListener: NewsClickListener) : RecyclerView.Adapter<NewsViewHolder>() {

    var news: List<NewsProperty> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val withDataBinding: NewsItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), NewsViewHolder.LAYOUT,
            parent,
            false
        )
        return NewsViewHolder(withDataBinding)
    }

    override fun getItemCount() = news.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind.also {
            it.news = news[position]
            it.clickListener = clickListener
        }
    }

}
class NewsClickListener(val clickListener: (news: NewsProperty) -> Unit) {
    fun onClick(news: NewsProperty) = clickListener(news)
}

class NewsViewHolder(val bind: NewsItemBinding) : RecyclerView.ViewHolder(bind.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.news_item
    }
}