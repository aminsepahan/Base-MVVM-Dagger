package com.amin.sample.base

import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

/*
VH: LoginView Holder
M: model for list
MC: model to be passed when clicked
*/
abstract class BaseRecyclerAdapter<VH : BaseViewHolder<M>, M, MC>
    : RecyclerView.Adapter<VH>(), BaseListContract.BaseAdapter<M> {

    var subscribe: Disposable? = null
    val clickSubject = PublishSubject.create<MC>()
    val clickEvent: Observable<MC> = clickSubject
    var modelList: MutableList<M> = mutableListOf()

    override fun setModelListForAdapter(modelList: MutableList<M>) {
        this.modelList = modelList
    }

    override fun addItems(modelList: MutableList<M>) {

        this.modelList.addAll(modelList)
    }

    private fun setList(list: List<M>?) {
        if (list != null) {
            this.modelList = list.toMutableList()
        } else {
            this.modelList.clear()
        }
        notifyDataSetChanged()

    }

    fun updateList(list: List<M>?) {
        list?.let {
            this.modelList.addAll(list)
            notifyItemRangeInserted(modelList.size - list.size, list.size)
        }
    }

    fun isEmpty(): Boolean {
        return modelList.isNullOrEmpty()
    }

    fun clear() {
        setList(emptyList())
    }

}