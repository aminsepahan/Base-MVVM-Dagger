package com.amin.sample.ui.imgur

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.databinding.DataBindingUtil
import com.amin.sample.R
import com.amin.sample.base.BaseRecyclerAdapter
import com.amin.sample.base.BaseViewHolder
import com.amin.sample.databinding.ItemImageImgurBinding
import com.amin.sample.model.ImgurImage
import com.amin.sample.model.Transition

class ImgurAdapter :
    BaseRecyclerAdapter<BaseViewHolder<ImgurImage>, ImgurImage, Transition>() {
    override fun onBindViewHolder(holder: BaseViewHolder<ImgurImage>, position: Int) {
        holder.bindItems(modelList[position])
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding: ItemImageImgurBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_image_imgur,
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    inner class ViewHolder(val binding: ItemImageImgurBinding) :
        BaseViewHolder<ImgurImage>(binding) {

        init {
            binding.cv.setOnClickListener {
                clickSubject.onNext(
                    Transition(
                        model = modelList[layoutPosition],
                        image = modelList[layoutPosition].getImageLink()!!,
                        title = modelList[layoutPosition].title,
                        imageTransition = binding.coverImageView.transitionName,
                        titleTransition = binding.caption.transitionName
                    )

                )
            }
        }

        @SuppressLint("SetTextI18n")
        override fun bindItems(model: ImgurImage) {
            binding.model = model
            val constraintSet = ConstraintSet()
            constraintSet.clone(binding.itemLay)
            model.apply {
                constraintSet.setDimensionRatio(
                    R.id.coverImageView,
                    "1:${if (isAlbum) coverHeight / coverWidth else height / width}"
                )
            }
            constraintSet.applyTo(binding.itemLay)
        }
    }
}