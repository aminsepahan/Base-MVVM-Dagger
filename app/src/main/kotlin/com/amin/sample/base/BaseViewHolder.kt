package com.nyp.kartak.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<M>(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bindItems(model: M)
}