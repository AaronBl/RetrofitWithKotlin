package com.example.abautista.kotlinretrofit

import android.provider.ContactsContract
import com.example.abautista.kotlinretrofit.CallBacks.ContactService
import com.example.abautista.kotlinretrofit.CallBacks.ContactsCallBack
import com.example.abautista.kotlinretrofit.Models.ApiError
import com.example.abautista.kotlinretrofit.Models.Contact
import com.example.abautista.kotlinretrofit.Models.resultResponse

class ContactsIterator(private val contactsCallBack: ContactsCallBack) {

    private val contactsServices: ContactService by lazy {
        ServiceGenerator.retrofit.create(ContactService::class.java)
    }

    fun getContacts(numberOfContacts: Int){

        this.contactsServices.getContacts(numberOfContacts)
                .enqueue(object : RetrofitCallback<resultResponse<Contact>>(){
                    override fun onResponseSuccess(response: resultResponse<Contact>) {
                        contactsCallBack.onGetContatcsResponse(response.results)
                    }

                    override fun onError(apiError: ApiError) {
                        contactsCallBack.onError(apiError)
                    }
                })
    }
}