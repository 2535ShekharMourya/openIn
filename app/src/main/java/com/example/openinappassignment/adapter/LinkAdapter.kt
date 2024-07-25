package com.example.openinappassignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.openinappassignment.R
import com.example.openinappassignment.model.Link
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LinkAdapter(private val listener: ItemClicked) :
    RecyclerView.Adapter<LinkAdapter.ViewHolder>() {
    private val links = ArrayList<Link>()

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val tvTitle: TextView = item.findViewById(R.id.tv_title)
        val image: ImageView = item.findViewById(R.id.imageView)
        val tvDate: TextView = item.findViewById(R.id.tv_date)
        val tvClickCount: TextView = item.findViewById(R.id.tv_click_count)
        val tvLink: TextView = item.findViewById(R.id.tv_link)
        val rlMain: View = item.findViewById(R.id.rl_main)
        val rlLink: View = item.findViewById(R.id.rl_link)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_links, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return links.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = links[position]
        holder.tvTitle.text = item.title
        holder.tvDate.text = formatDateString(item.createdAt)
        holder.tvClickCount.text = item.totalClicks.toString()
        holder.tvLink.text = item.webLink
        Glide.with(holder.image).load(item.originalImage).into(holder.image)
        holder.rlMain.setOnClickListener {
            listener.onItemClicked(item)
        }
        holder.rlLink.setOnClickListener {
            listener.onLinkClicked(item)
        }
    }

    interface ItemClicked {
        fun onItemClicked(item: Link)
        fun onLinkClicked(item: Link)
    }

    fun submitList(list: List<Link>) {
        links.clear()
        links.addAll(list)
        notifyDataSetChanged()
    }

    private fun formatDateString(inputDateTime: String): String {
        val inputFormatter = DateTimeFormatter.ISO_DATE_TIME
        val dateTime = LocalDateTime.parse(inputDateTime, inputFormatter)
        val outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
        return dateTime.format(outputFormatter)
    }
}