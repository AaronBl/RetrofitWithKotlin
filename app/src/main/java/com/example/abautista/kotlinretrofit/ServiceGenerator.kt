package com.example.abautista.kotlinretrofit

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceGenerator {

    private const val BASE_URL: String = "http://randomuser.me/"


    private val okHttpClientBuilder = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)


    private val gson = GsonBuilder()
            .setExclusionStrategies(object : ExclusionStrategy {


                override fun shouldSkipField(f: FieldAttributes?): Boolean {
                    return f?.annotations?.none { it is SerializedName } ?: true
                }

                override fun shouldSkipClass(clazz: Class<*>?): Boolean {
                    return false
                }

            }).create()


    private val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(this.gson))

    private val okHttpClient = this.okHttpClientBuilder.build()

    val retrofit: Retrofit = this.retrofitBuilder.client(this.okHttpClient).build()

}