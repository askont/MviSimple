package com.yakow.weber.mvisimple.model.system

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.yakow.weber.mvisimple.R

/**
 * Created on 29.05.19
 * @author YWeber */

class ImageDownloader {
    fun loadImage(view: ImageView, urlContent: String) {
        Glide.with(view.context)
            .load(urlContent)
            .centerCrop()
            .placeholder(R.drawable.ic_broken_image_red)
            .into(view)
    }
}