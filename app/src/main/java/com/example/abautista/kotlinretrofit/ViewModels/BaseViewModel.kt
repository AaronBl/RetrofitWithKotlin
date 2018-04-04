package com.example.abautista.kotlinretrofit.ViewModels

import com.example.abautista.kotlinretrofit.Models.ApiError

interface BaseViewModel {

    fun displayApiError(apiError: ApiError)

}