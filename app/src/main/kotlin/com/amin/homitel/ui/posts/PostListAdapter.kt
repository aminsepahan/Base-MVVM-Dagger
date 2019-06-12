package com.amin.homitel.ui.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.amin.homitel.R
import com.amin.homitel.databinding.ItemPostBinding
import com.amin.homitel.model.Post

class PostListAdapter : RecyclerView.Adapter<PostListAdapter.ViewHolder>() {
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

    class ViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            binding.post = post
            binding.executePendingBindings()
        }
    }
}