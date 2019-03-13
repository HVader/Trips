package com.krichen.trips.trips

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.krichen.trips.R
import com.krichen.trips.data.Trip
import com.krichen.trips.databinding.TripItemBinding

import android.databinding.DataBindingUtil
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.krichen.trips.utils.Constants
import com.squareup.picasso.Picasso


class TripsAdapter(
        private val tripsViewModel: TripsViewModel
): RecyclerView.Adapter<TripsAdapter.TripsViewHolder>() {


    private var data = emptyList<Trip>()
    private lateinit var layoutInflater: LayoutInflater
    private val item = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripsViewHolder {
        this.layoutInflater = LayoutInflater.from(parent.context)
        val tripItemBinding: TripItemBinding= DataBindingUtil.inflate(layoutInflater, R.layout.trip_item,parent,false)
        return TripsViewHolder(tripItemBinding)

    }


    override fun getItemCount(): Int {
       return data.size
    }

    override fun onBindViewHolder(holder: TripsViewHolder, position: Int) {
        holder.binding.trip= data[position]
        val tripItemListener= object :TripItemListener{
            override fun onTripClicked(view: View, trip: Trip) {
                tripsViewModel.openTrip(trip.id)
                Log.d("TestListener",trip.pilot?.name)
            }
        }
        holder.binding.tripListener=tripItemListener
    }

    fun setData(data: List<Trip>?) {
        this.data = data ?: this.data
        notifyDataSetChanged()
    }
    companion object {
        @JvmStatic
        @BindingAdapter("items")
        fun RecyclerView.bindItems(items: List<Trip>) {
            val adapter = adapter as TripsAdapter
            adapter.setData(items)
        }


        @JvmStatic
        @BindingAdapter("profileImage")
        fun loadImage(view: ImageView, imageUrl: String) {
            val image = Constants.BASE_URL+imageUrl
            Picasso.with(view.context)
                    .load(image)
                    .into(view)
        }
    }

    inner class TripsViewHolder(val binding: TripItemBinding) : RecyclerView.ViewHolder(binding.root)




}