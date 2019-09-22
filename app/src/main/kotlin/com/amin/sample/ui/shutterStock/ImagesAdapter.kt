package com.amin.sample.ui.shutterStock

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.amin.sample.R
import com.amin.sample.databinding.ItemImageBinding
import com.amin.sample.databinding.ItemPostBinding
import com.amin.sample.model.Post

class ImagesAdapter : RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {
    private lateinit var postList: List<Post>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemPostBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_post, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    override fun getItemCount(): Int {
        return if (::postList.isInitialized) postList.size else 0
    }

    fun updatePostList(postList: List<Post>?) {
        postList?.let {
            this.postList = postList
            notifyDataSetChanged()
        }
    }

    class ViewHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: Post) {
            binding.model = model
        }
    }
}