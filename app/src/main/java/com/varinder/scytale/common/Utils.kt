package com.varinder.scytale.common

import android.app.Activity
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.snackbar.Snackbar
import com.varinder.scytale.R


fun ImageView.loadImageFromGlide(url: String?) {
    if(url!=null) {
        Glide.with(this)
            .load(url)
            .error(R.drawable.ic_camera)
            .centerCrop()
            .placeholder(com.google.android.material.R.drawable.ic_clock_black_24dp)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(this)
    }

}

fun Context.showNegativeAlerter(message: String) {
    val snakbar = Snackbar.make((this as Activity).findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
    snakbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        .maxLines = 5
    snakbar.show()
}

fun getRealPathFromURI(context: Context, contentUri: Uri?): String? {
    var cursor: Cursor? = null
    return try {
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        cursor = context.contentResolver.query(contentUri!!, proj, null, null, null)
        val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        cursor.getString(column_index)
    } finally {
        cursor?.close()
    }
}