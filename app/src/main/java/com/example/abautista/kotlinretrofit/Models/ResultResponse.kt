package com.example.abautista.kotlinretrofit.Models

import com.google.gson.annotations.SerializedName

data class ResultResponse<out T> (@SerializedName("results") private val _results:List<T>?) {

    val results : List<T> get() = this._results ?: emptyList()

}