package com.mvvm.cryptocapital.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapters<BINDING : ViewDataBinding,VM:ViewModel,T>(
    var data: List<T>?,
    var viewModel: VM
) : RecyclerView.Adapter<com.mvvm.cryptocapital.base.BaseViewHolder<BINDING>>() {

    @get:LayoutRes
    abstract val layoutId: Int

    abstract fun bind(binding: BINDING, item: T, position: Int, holder: com.mvvm.cryptocapital.base.BaseViewHolder<BINDING>, viewModel: VM)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): com.mvvm.cryptocapital.base.BaseViewHolder<BINDING> {
        val binder = DataBindingUtil.inflate<BINDING>(
            LayoutInflater.from(parent.context),
            layoutId,
            parent,
            false
        )
        return com.mvvm.cryptocapital.base.BaseViewHolder(binder)
    }

    override fun onBindViewHolder(holder: com.mvvm.cryptocapital.base.BaseViewHolder<BINDING>, position: Int) {
        data?.get(position)?.let { bind(holder.binder, it, position,holder,viewModel) }
    }

    override fun getItemCount(): Int = data?.size ?: 0
}

class BaseViewHolder<BINDING : ViewDataBinding>(val binder: BINDING) :
    RecyclerView.ViewHolder(binder.root)