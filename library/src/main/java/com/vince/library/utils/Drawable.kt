package com.vince.library.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable

/**
 * <p>作者：Night  2019/1/29 13:57
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：Drawable扩展工具类
 */

fun Drawable.toBitmap(): Bitmap {

    //Get drawable's width and height
    val width = intrinsicWidth
    val height = intrinsicHeight

    //Get drawable's color style
    val config = if (opacity != PixelFormat.OPAQUE) Bitmap.Config.ARGB_8888 else Bitmap.Config.RGB_565
    //Create bitmap
    val bitmap = Bitmap.createBitmap(width, height, config)
    val canvas = Canvas(bitmap)
    setBounds(0, 0, width, height)
    //Draw bitmap
    draw(canvas)

    return bitmap
}