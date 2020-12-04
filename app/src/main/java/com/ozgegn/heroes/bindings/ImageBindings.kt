package com.ozgegn.heroes.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun bindImageUrl(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) Glide.with(view.context).load(url).into(view)
}