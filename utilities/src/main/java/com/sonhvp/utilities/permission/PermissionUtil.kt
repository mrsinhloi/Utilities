package com.sonhvp.utilities.permission

import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

infix fun AppCompatActivity.havePerm(permission: String): Boolean = ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
infix fun AppCompatActivity.havePerms(permissions: Array<String>): Boolean = permissions.all { ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED }

fun AppCompatActivity.requestPerm(permission: String, requestCode: Int = 0) { ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)}
fun AppCompatActivity.requestPerms(permissions: Array<String>, requestCode: Int = 0) { ActivityCompat.requestPermissions(this, permissions, requestCode)}

infix fun Context.havePerm(permission: String): Boolean = ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
infix fun Context.havePerms(permissions: Array<String>): Boolean = permissions.all { ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED }