package com.amin.sample.ui.shutterStock

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.amin.sample.R
import com.amin.sample.base.BaseRecyclerAdapter
import com.amin.sample.base.BaseViewHolder
import com.amin.sample.databinding.ItemImageShutterBinding
import com.amin.sample.model.ShutterStockImage
import com.amin.sample.model.Transition

class ShutterStockAdapter :
    BaseRecyclerAdapter<BaseViewHolder<ShutterStockImage>, ShutterStockImage, Transition>() {
    override fun onBindViewHolder(holder: BaseViewHolder<ShutterStockImage>, position: Int) {
        holder.bindItems(modelList[position])
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding: ItemImageShutterBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_image_shutter,
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    inner class ViewHolder(val binding: ItemImageShutterBinding) :
        BaseViewHolder<ShutterStockImage>(binding) {

        init {
            binding.cv.setOnClickListener {
                clickSubject.onNext(
                    Transition(
                        imageUrl = modelList[layoutPosition].assets.hugeThumb.url,
                        title = modelList[layoutPosition].description,
                        imageTransition = binding.coverImageView
                    )
                )
            }
        }

        @SuppressLint("SetTextI18n")
        override fun bindItems(model: ShutterStockImage) {
            binding.model = model
        }
    }
}