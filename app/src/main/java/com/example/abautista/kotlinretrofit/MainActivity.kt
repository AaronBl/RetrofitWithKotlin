package com.example.abautista.kotlinretrofit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.text.Editable
import android.text.TextWatcher
import com.example.abautista.kotlinretrofit.Models.ApiError
import com.example.abautista.kotlinretrofit.Models.Contact
import com.example.abautista.kotlinretrofit.ViewModels.ContactsViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ContactsViewModel {

    private val presenter: ContactsPresenter by lazy { ContactsPresenter(this) }



    private  val contactAdapter:ContactsAdapter  by lazy {ContactsAdapter(this, mutableListOf())}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.recycler_view.adapter = this.contactAdapter

        this.refresh_layout.setOnRefreshListener {
            this.presenter.refreshContacts(30,"")
            this.toggle.isChecked=false
        }

        this.update_buttom.setOnClickListener {
            this.presenter.updateContacts(30,"")
        }

        this.toggle.setOnClickListener {
            if(this.toggle.isChecked){
                this.presenter.updateContacts(30,"female")
            }else{
                this.presenter.updateContacts(30,"male")
            }
        }
        
        this.text_filter.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
               
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.filterContacts(text_filter.text.toString().trim())
            }

        })


        this.presenter.updateContacts(30,"")
    }

    override fun setProgressVisibility(progressVisibility: Int, emptyMessage: Int, listVisibility: Int) {
        this.progress_container.visibility = progressVisibility
        this.empty_message_container.visibility = emptyMessage
        this.refresh_layout.visibility = listVisibility
    }

    override fun setRefreshing(refreshing: Boolean) {
        this.refresh_layout.isRefreshing = refreshing
    }

    override fun displayContacts(contacts: List<Contact>) {
            this.contactAdapter.updateContacts(contacts)

    }

    override fun displayApiError(apiError: ApiError) {

        val snack= Snackbar.make(this.main_container, apiError.error, Snackbar.LENGTH_INDEFINITE)
        snack.setAction(android.R.string.ok){
            snack.dismiss()
        }
        snack.show()
    }


}
