package com.sonhvp.utilities.io

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream

fun String.getBimap() = BitmapFactory.decodeFile(this)

fun Bitmap.scaleTo(width: Int, height: Int, filter: Boolean = true) = Bitmap.createScaledBitmap(this, width, height, filter)

fun Bitmap.saveTo(path: String, format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG, quality: Int = 100) {
    compress(format, quality, FileOutputStream(path))
}

fun Bitmap.saveTo(file: File, format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG, quality: Int = 100) {
    compress(format, quality, FileOutputStream(file))
}

fun Context.getFileUri(authority: String, file: File): Uri = FileProvider.getUriForFile(applicationContext, authority, file)

infix fun Context.getPathFor(uri: Uri): String? {
    val projection = arrayOf(MediaStore.MediaColumns.DATA)
    return contentResolver.query(uri, projection, null, null, null)?.run {
        val columnIndex = getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        moveToFirst()
        val path = getString(columnIndex)
        close()
        return@run path
    }
}