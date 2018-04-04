package com.example.abautista.kotlinretrofit

import android.view.View
import com.example.abautista.kotlinretrofit.CallBacks.ContactsCallBack
import com.example.abautista.kotlinretrofit.Models.ApiError
import com.example.abautista.kotlinretrofit.Models.Contact
import com.example.abautista.kotlinretrofit.ViewModels.ContactsViewModel

class ContactsPresenter(private val contactsViewModel: ContactsViewModel): ContactsCallBack{


    private val iterator: ContactsIterator by lazy { ContactsIterator(this) }

    private var contacts: List<Contact> = emptyList()

    fun updateContacts(numbreOfContacts: Int,gender: String){
        this.contactsViewModel.setProgressVisibility(
                View.VISIBLE,
                View.INVISIBLE,
                View.VISIBLE)
        this.iterator.getContacts(numbreOfContacts,gender)
    }

    fun refreshContacts(numbreOfContacts: Int,gender: String=""){
        this.contactsViewModel.setProgressVisibility(
                View.INVISIBLE,
                View.INVISIBLE,
                View.VISIBLE)

        this.iterator.getContacts(numbreOfContacts,gender)
    }


    override fun onGetContatcsResponse(Contacts: List<Contact>) {
        this.contacts=Contacts
        this.contactsViewModel.setProgressVisibility(
                View.INVISIBLE,
                if(this.contacts.isEmpty()) View.VISIBLE else View.INVISIBLE,
                if(this.contacts.isEmpty()) View.INVISIBLE else View.VISIBLE)
        this.contactsViewModel.setRefreshing(false)
        this.contactsViewModel.displayContacts(this.contacts)
    }

    override fun onError(apiError: ApiError) {
        this.contactsViewModel.setProgressVisibility(
                View.INVISIBLE,
                if(this.contacts.isEmpty()) View.VISIBLE else View.INVISIBLE,
                if(this.contacts.isEmpty()) View.INVISIBLE else View.VISIBLE)
        this.contactsViewModel.setRefreshing(false)
        this.contactsViewModel.displayApiError(apiError)
    }

    fun filterContacts(query: String){
        this.iterator.filterContacts(query,this.contacts)
    }

    override fun filterContactsComplete(contact: List<Contact>) {
        this.contactsViewModel.displayContacts(contact)
    }


}