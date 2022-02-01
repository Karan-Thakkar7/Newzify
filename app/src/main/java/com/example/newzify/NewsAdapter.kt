package com.example.newzify

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newzify.data.Article
import kotlinx.android.synthetic.main.news_list.view.*


class NewsAdapter(val context: Context, val article: List<Article>,val Listner:OnClicked) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    interface OnClicked {
        fun OnitemClicked(position: Int, url:String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.news_list, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val CurrenItem = article[position]
        holder.textview1.text = CurrenItem.title
        holder.textview2.text = CurrenItem.description
        Glide.with(context).load(CurrenItem.urlToImage).into(holder.imageview)
        holder.itemView.setOnClickListener {
                Listner.OnitemClicked(position,CurrenItem.url)
        }
    }


    override fun getItemCount() = article.size


    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textview1 = itemView.textView1
        val textview2 = itemView.textView2
        val imageview = itemView.imageView

    }
}