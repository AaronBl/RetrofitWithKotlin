package com.example.abautista.kotlinretrofit.Models

import com.google.gson.annotations.SerializedName

data class resultResponse<out T> (@SerializedName("result") private val _results:List<T>?) {

    val results : List<T> get() = this.results ?: emptyList()

}