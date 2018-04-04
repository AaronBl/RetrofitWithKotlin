package com.example.abautista.kotlinretrofit.CallBacks

import com.example.abautista.kotlinretrofit.Models.Contact

interface ContactsCallBack: BaseCallback {

    fun onGetContatcsResponse(Contacts: List<Contact>)

}