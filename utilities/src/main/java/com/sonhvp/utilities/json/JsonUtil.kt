package com.sonhvp.utilities.json

import org.json.JSONArray
import org.json.JSONObject

fun Any.toJSON(): JSONObject = this as JSONObject
fun String.toJSON(): JSONObject = JSONObject(this)

fun jsonObj(block: JSONObject.() -> Unit): JSONObject = JSONObject().apply(block)
fun jsonArray(block: JSONArray.() -> Unit): JSONArray = JSONArray().apply(block)