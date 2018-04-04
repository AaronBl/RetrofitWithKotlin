package com.example.abautista.kotlinretrofit.CallBacks

import com.example.abautista.kotlinretrofit.Models.Contact
import com.example.abautista.kotlinretrofit.Models.ResultResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ContactService {

    @GET("api/")
    fun getContacts(@Query("results") numberOfResult: Int) : Call<ResultResponse<Contact>>
}