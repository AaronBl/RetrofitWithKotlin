package com.example.abautista.kotlinretrofit.Models

import com.google.gson.annotations.SerializedName

data class Contact(@SerializedName("name") val name: Name?,
                   @SerializedName("email") private val _email: String?,
                   @SerializedName("cell") private val _cell: String?,
                   @SerializedName("picture") val picture: Picture?) {

    val email: String get()=this._email ?: ""
    val cell: String get()=this._cell?: ""

    data class Name(@SerializedName("title") private val _title: String?,
                    @SerializedName("first") private val _first: String?,
                    @SerializedName("last") private val _last: String?) {

        val fullName: String get() = "${this._title ?: ""} ${this._first ?: ""} ${this._last ?: ""}"
    }

    data class Picture(@SerializedName("large") private val _large: String?,
                       @SerializedName("medium") private val _medium: String?,
                       @SerializedName("thumbnail") private val _thumbnail: String?) {

        val large: String get() = this._large ?: ""
        val medium: String get() = this._medium ?: ""
        val thumbnail: String get() = this._thumbnail ?: ""
    }

}