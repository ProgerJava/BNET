package com.pestControlDrugs.bnet.tools

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

fun showToast (context: Context, id: Int) {
    Toast.makeText(context, context.getString(id), Toast.LENGTH_SHORT).show()
}
fun showToast (context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}
fun picassoLoad (url: String, image: ImageView, context: Context) {
    Picasso.get().load(url).into(image, object : Callback {
        override fun onSuccess() {}

        override fun onError(e: Exception) {
            showToast(context, e.message.toString())
        }
    })
}