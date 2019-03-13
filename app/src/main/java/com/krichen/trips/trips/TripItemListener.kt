package com.krichen.trips.trips

import android.view.View
import com.krichen.trips.data.Trip

interface TripItemListener {
     fun onTripClicked(view: View, trip:Trip)
}