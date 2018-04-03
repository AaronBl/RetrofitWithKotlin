package com.example.abautista.kotlinretrofit.Models

import com.google.gson.annotations.SerializedName

data class ApiError(@SerializedName("error") private val _error: String?) {

    val error: String get() = this._error ?: ""
}