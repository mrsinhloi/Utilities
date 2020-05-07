package com.sonhvp.utilities.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager

fun View.toBitmap(): Bitmap = this.apply {
    val display = (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
    val point = Point()
    display.getSize(point)
    //layoutParams = ViewGroup.LayoutParams(width, height)
    measure(
            //ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
            View.MeasureSpec.makeMeasureSpec(point.x, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(point.y, View.MeasureSpec.EXACTLY)
    )
}.run {
    val bitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    layout(0, 0, measuredWidth, measuredHeight)
    canvas.drawColor(Color.WHITE)
    draw(canvas)

    return@run bitmap
}