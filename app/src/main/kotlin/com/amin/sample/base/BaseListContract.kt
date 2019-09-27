package com.amin.sample.base

/**
 * Created by Amin on 16/01/2019.
 */
class BaseListContract {


    interface BaseAdapter<T> {
        fun setModelListForAdapter(modelList: MutableList<T>)
        fun addItems(modelList: MutableList<T>)
        fun notifyDataSetChanged()
    }
}
