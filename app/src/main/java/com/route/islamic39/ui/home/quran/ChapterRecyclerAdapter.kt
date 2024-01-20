package com.route.islamic39.ui.home.quran

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.route.islamic39.databinding.ItemChapterTitleBinding

class ChapterRecyclerAdapter(private val chaptersList: List<String>) :
    RecyclerView.Adapter<ChapterRecyclerAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val title = chaptersList[position]
        holder.bind(title)
        onItemClickListener?.let { listener ->
            holder.itemView.setOnClickListener {
                listener.onItemClick(title, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding: ItemChapterTitleBinding =
            ItemChapterTitleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(viewBinding)
    }

    override fun getItemCount(): Int = chaptersList.size
    var onItemClickListener: OnItemClickListener? = null

    fun interface OnItemClickListener {
        fun onItemClick(item: String, position: Int)
    }

    class ViewHolder(private val itemBinding: ItemChapterTitleBinding) : RecyclerView.ViewHolder(
        itemBinding.root
    ) {
        fun bind(title: String) {
            itemBinding.title.text = title
        }
    }
}
