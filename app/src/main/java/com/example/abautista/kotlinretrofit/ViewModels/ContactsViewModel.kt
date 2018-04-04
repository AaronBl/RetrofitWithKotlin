package com.example.abautista.kotlinretrofit.ViewModels

import com.example.abautista.kotlinretrofit.Models.Contact

interface ContactsViewModel: BaseViewModel {

    fun setProgressVisibility(progressVisibility: Int, emptyMessage: Int, listVisibility: Int)

    fun setRefreshing(refreshing:Boolean)

    fun displayContacts(contacts: List<Contact>)

}