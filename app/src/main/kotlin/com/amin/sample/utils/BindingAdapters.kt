package com.amin.sample.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.amin.sample.base.Configs
import com.amin.sample.utils.extensions.getParentActivity
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && visibility != null) {
        visibility.observe(
            parentActivity,
            Observer { value -> view.visibility = value ?: View.VISIBLE })
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value ?: "" })
    }
}

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("imageUrl")
fun downloadWithPicasso(view: ImageView, imageUrl: String?) {
    if (imageUrl != null) {
        when (Configs.sampleMode) {
            Configs.SampleTypes.SHUTTER_STOCK -> {
                Picasso.get().load(imageUrl).into(view)
            }
            Configs.SampleTypes.IMGUR -> {
                Glide.with(view).load(imageUrl).centerInside().into(view)
            }
            Configs.SampleTypes.BLOG_POSTS -> {
                Picasso.get().load(imageUrl).into(view)
            }
            else -> {
            }
        }
    }
}