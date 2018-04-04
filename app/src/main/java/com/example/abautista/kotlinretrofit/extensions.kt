package com.example.abautista.kotlinretrofit

import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

/**
 * Created by dbenitez on 4/4/2018.
 */
fun CircleImageView.loadfromurl(url: String?){

    Picasso.with(this.context).load(url).error(R.drawable.ic_profile).placeholder(R.drawable.ic_profile).into(this)


}