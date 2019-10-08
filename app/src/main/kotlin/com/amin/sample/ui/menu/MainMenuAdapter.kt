package com.amin.sample.ui.menu

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.amin.sample.R
import com.amin.sample.base.BaseRecyclerAdapter
import com.amin.sample.base.BaseViewHolder
import com.amin.sample.base.Configs
import com.amin.sample.databinding.ItemMainMenuBinding
import com.amin.sample.model.MainMenuItem

class MainMenuAdapter :
    BaseRecyclerAdapter<BaseViewHolder<MainMenuItem>, MainMenuItem, Configs.SampleTypes>() {
    override fun onBindViewHolder(holder: BaseViewHolder<MainMenuItem>, position: Int) {
        holder.bindItems(modelList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding: ItemMainMenuBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_main_menu,
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    inner class ViewHolder(val binding: ItemMainMenuBinding) :
        BaseViewHolder<MainMenuItem>(binding) {

        init {
            binding.cv.setOnClickListener {
                clickSubject.onNext(Configs.SampleTypes.fromInt(layoutPosition)!!)
            }
        }

        @SuppressLint("SetTextI18n")
        override fun bindItems(model: MainMenuItem) {
            binding.model = model
            binding.coverImageView.setImageResource(model.imageId)
        }
    }
}