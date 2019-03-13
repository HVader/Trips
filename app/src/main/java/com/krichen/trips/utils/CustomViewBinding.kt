package com.krichen.trips.utils

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.widget.ImageView

import com.krichen.trips.data.Trip
import com.krichen.trips.trips.TripsAdapter
import com.squareup.picasso.Picasso


@BindingAdapter("items")
fun RecyclerView.bindItems(items:List<Trip>){
    val adapter = adapter as TripsAdapter
    adapter.setData(items)
}



@BindingAdapter("profileImage")
fun loadImage (view:ImageView, imageUrl:String?){
    val image = Constants.BASE_URL + imageUrl
    Picasso.with(view.context)
        .load(image)
        .into(view)
}




