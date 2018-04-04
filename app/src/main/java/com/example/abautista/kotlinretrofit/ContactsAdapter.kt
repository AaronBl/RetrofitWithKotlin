package com.example.abautista.kotlinretrofit

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.abautista.kotlinretrofit.Models.Contact
import de.hdodenhof.circleimageview.CircleImageView

class ContactsAdapter (private val context: Context, private  val contacts: MutableList<Contact>) :
                            RecyclerView.Adapter<ContactsAdapter.ContactsViewholder>(){

    private val layoutInflater : LayoutInflater by lazy { LayoutInflater.from(this.context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewholder {
            val View = this.layoutInflater.inflate(R.layout.contacts_layout_item,parent,false)
            return ContactsViewholder(View)
    }

    override fun getItemCount(): Int = this.contacts.size

    override fun onBindViewHolder(holder: ContactsViewholder, position: Int) {

        val contact = this.contacts[position]
        holder?.nameTextView?.text = contact.name?.fullName
        holder?.emailTextView?.text = contact.email
        holder?.cellPhoneTextView?.text = contact.cell
        holder?.profileImage?.loadFromUrl(contact.picture?.medium)


    }

    fun updateContacts(contacts: List<Contact>){
        this.contacts.clear()
        this.contacts.addAll(contacts)
        this.notifyDataSetChanged()
    }

    class ContactsViewholder(itemView: View? ): RecyclerView.ViewHolder(itemView){
        val profileImage: CircleImageView? = itemView?.findViewById(R.id.profile_image)
        val nameTextView: TextView?  = itemView?.findViewById(R.id.text_view_contact_name)
        val emailTextView: TextView? = itemView?.findViewById(R.id.text_view_email)
        val cellPhoneTextView: TextView? =itemView?.findViewById(R.id.text_view_cellphone)
    }
}