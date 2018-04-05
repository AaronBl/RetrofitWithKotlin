package com.example.abautista.kotlinretrofit


import android.util.Log
import com.example.abautista.kotlinretrofit.CallBacks.ContactService
import com.example.abautista.kotlinretrofit.CallBacks.ContactsCallBack
import com.example.abautista.kotlinretrofit.Models.ApiError
import com.example.abautista.kotlinretrofit.Models.Contact
import com.example.abautista.kotlinretrofit.Models.resultResponse

class ContactsIterator(private val contactsCallBack: ContactsCallBack) {

    private val contactsServices: ContactService by lazy {
        ServiceGenerator.retrofit.create(ContactService::class.java)
    }

    fun getContacts(numberOfContacts: Int, gender: String=""){

        this.contactsServices.getContacts(numberOfContacts,gender)
                .enqueue(object : RetrofitCallback<resultResponse<Contact>>(){
                    override fun onResponseSuccess(response: resultResponse<Contact>) {
                        contactsCallBack.onGetContatcsResponse(response.results)
                        Log.e("","")
                    }

                    override fun onError(apiError: ApiError) {
                        contactsCallBack.onError(apiError)
                    }
                })
    }

    fun filterContacts(query: String, contacts: List<Contact>){
        if(query.isBlank()){
            contactsCallBack.filterContactsComplete(contacts)
        }else{
            val filteredContacts=contacts.filter { contact->
                val name =contact.name?.fullName?: ""
                val email=contact.email
                name.contains(query,true) or email.contains(query,true) }
            contactsCallBack.filterContactsComplete(filteredContacts)
        }

    }



}