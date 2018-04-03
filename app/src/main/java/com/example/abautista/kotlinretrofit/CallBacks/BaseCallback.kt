package com.example.abautista.kotlinretrofit.CallBacks

import com.example.abautista.kotlinretrofit.Models.ApiError

interface BaseCallback {

    fun onError(apiError: ApiError)
}