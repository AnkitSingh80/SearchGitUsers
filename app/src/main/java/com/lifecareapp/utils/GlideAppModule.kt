package com.lifecareapp.utils

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.lifecareapp.R

@GlideModule
class GlideAppModule : AppGlideModule()
{
    override fun applyOptions(context: Context?, builder: GlideBuilder?)
    {
        val requestOptions = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .placeholder(R.drawable.profile_placeholder)
                .error(R.color.grey_light)

        builder?.setDefaultRequestOptions(requestOptions)
    }
}