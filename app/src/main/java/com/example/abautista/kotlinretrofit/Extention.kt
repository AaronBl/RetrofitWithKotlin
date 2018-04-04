package com.example.abautista.kotlinretrofit


import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

fun CircleImageView.loadFromUrl(url: String?) {

    Picasso.with(this.context).load(url).error(R.drawable.ic_profile).placeholder(R.drawable.ic_profile).into(this)

}