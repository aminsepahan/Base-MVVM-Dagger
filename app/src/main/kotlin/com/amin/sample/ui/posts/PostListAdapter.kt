package com.amin.sample.ui.posts

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.amin.sample.R
import com.amin.sample.base.BaseRecyclerAdapter
import com.amin.sample.base.BaseViewHolder
import com.amin.sample.databinding.ItemPostBinding
import com.amin.sample.model.Post
import com.amin.sample.model.Transition
import kotlinx.android.synthetic.main.item_post.view.*

class PostListAdapter : BaseRecyclerAdapter<BaseViewHolder<Post>, Post, Transition>() {
    override fun onBindViewHolder(holder: BaseViewHolder<Post>, position: Int) {
        holder.bindItems(modelList[position])
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding: ItemPostBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_post,
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    inner class ViewHolder(val binding: ItemPostBinding) :
        BaseViewHolder<Post>(binding) {

        init {
            binding.cv.setOnClickListener {
                clickSubject.onNext(
                    Transition(
                        imageUrl = it.postImage.tag.toString(),
                        title = modelList[layoutPosition].title,
                        imageTransition = binding.postImage,
                        titleTransition = binding.postTitle
                    )
                )
            }
        }

        @SuppressLint("SetTextI18n")
        override fun bindItems(model: Post) {
            binding.model = model
            binding.postImage.transitionName = "imageView-$adapterPosition"
        }
    }
}