package com.varinder.scytale.common

import android.graphics.Paint
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object BindingAdapters {
    @BindingAdapter(value = ["setRecyclerAdapter"], requireAll = false)
    @JvmStatic
    fun setRecyclerAdapter(
        recyclerView: RecyclerView,
        adapter: RecyclerView.Adapter<*>
    ) {
        recyclerView.adapter = adapter
    }

    @BindingAdapter(value = ["decryptText"], requireAll = false)
    @JvmStatic
    fun setDecryptText(
        view: TextView, value: String?
    ) {
        view.text = value?.decryptAES()
    }


}