package com.recyclerview.mynews

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.items.view.*

class NewsAdapter(val fragment: Fragment, val items: ArrayList<Articles>):
    RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items, parent,
            false)
        return NewsViewHolder(view)
     }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]
        holder.headingTV.text = currentItem.title
        holder.contentTV.text = currentItem.description
        holder.authorTV.text = currentItem.author
        holder.publishedTV.text = currentItem.publishedAt
        Glide.with(fragment).load(items[position].urlToImage).into(holder.imageView)
        holder.itemView.apply {
          cardView.setOnClickListener {
              val intent = Intent(context, WebViewActivity::class.java)
              intent.putExtra("url",items[position].url)
              context.startActivity(intent)

          }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}


class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val headingTV : TextView = itemView.findViewById(R.id.headingTV)
    val contentTV : TextView = itemView.findViewById(R.id.contentTV)
    val authorTV : TextView = itemView.findViewById(R.id.authorTV)
    val publishedTV : TextView = itemView.findViewById(R.id.publishedTV)
    val imageView : ImageView = itemView.findViewById(R.id.imageView)
    val cardView : CardView = itemView.findViewById(R.id.cardView)
}