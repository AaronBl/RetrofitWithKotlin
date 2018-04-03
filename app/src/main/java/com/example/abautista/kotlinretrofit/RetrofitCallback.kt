package com.example.abautista.kotlinretrofit

import android.support.design.widget.BaseTransientBottomBar
import com.example.abautista.kotlinretrofit.CallBacks.BaseCallback
import com.example.abautista.kotlinretrofit.Models.ApiError
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException


abstract class RetrofitCallback<T> : Callback<T>, BaseCallback {

    abstract fun onResponseSuccess(response: T)

    override fun onResponse(call: Call<T>?, response: Response<T>?) {
        if (response?.isSuccessful == true) {
            val responseData = response.body()
            if (responseData != null) {
                this.onResponseSuccess(responseData)
            } else {
                this.onError(ApiError( "The request body is null"))
            }
        } else {
            this.handleErrorResponse(response, this)
        }
    }

    override fun onFailure(call: Call<T>?, t: Throwable?) {
        this.onError(ApiError( t?.localizedMessage ?: ""))
    }

    private fun <R> handleErrorResponse(response: Response<R>?, baseServerCallBack: BaseCallback) {
        val converter: Converter<ResponseBody, ApiError> = ServiceGenerator.retrofit.responseBodyConverter(ApiError::class.java, arrayOfNulls(0))
        try {
            val errorBody = response?.errorBody()
            if (errorBody is ResponseBody) {
                val responseError = converter.convert(errorBody)
                baseServerCallBack.onError(responseError)
            } else {
                baseServerCallBack.onError(ApiError( "The request body is null"))
            }
        } catch (e: IOException) {
            baseServerCallBack.onError(ApiError( e.localizedMessage))
        }
    }
}