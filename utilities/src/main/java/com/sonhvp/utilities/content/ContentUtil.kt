package com.sonhvp.utilities.content

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.core.content.ContextCompat

infix fun Context.getColorCompat(colorId: Int) = ContextCompat.getColor(this, colorId)
infix fun Context.getDrawableCompat(drawableId: Int): Drawable = ContextCompat.getDrawable(this, drawableId)!!

fun Context.showPopupMenu(anchor: View, menuId: Int, onItemClick: (menuItem: MenuItem) -> Unit) {
    PopupMenu(this@showPopupMenu, anchor).apply {
        menuInflater.inflate(menuId, menu)
        setOnMenuItemClickListener { menuItem ->
            onItemClick.invoke(menuItem)
            true
        }
    }.show()
}