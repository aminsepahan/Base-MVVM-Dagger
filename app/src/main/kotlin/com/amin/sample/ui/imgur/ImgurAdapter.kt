package com.amin.sample.ui.shutterStock

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.amin.sample.R
import com.amin.sample.databinding.ItemImageBinding
import com.amin.sample.model.ShutterStockImage
import com.amin.sample.model.Transition
import com.amin.sample.base.BaseRecyclerAdapter
import com.amin.sample.base.BaseViewHolder

class ImgurAdapter :
    BaseRecyclerAdapter<BaseViewHolder<ShutterStockImage>, ShutterStockImage, Transition<ShutterStockImage>>() {
    override fun onBindViewHolder(holder: BaseViewHolder<ShutterStockImage>, position: Int) {
        holder.bindItems(modelList[position])
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding: ItemImageBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_image,
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    inner class ViewHolder(val binding: ItemImageBinding) :
        BaseViewHolder<ShutterStockImage>(binding) {

        init {
            binding.cv.setOnClickListener {
                clickSubject.onNext(Transition(modelList[layoutPosition], binding.coverImageView))
            }
        }

        @SuppressLint("SetTextI18n")
        override fun bindItems(model: ShutterStockImage) {
            binding.model = model
        }
    }
}