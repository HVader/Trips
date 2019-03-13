package com.krichen.trips.data.source

import android.support.annotation.NonNull
import com.krichen.trips.data.Trip

interface TripsDataSource {
    interface LoadTripsCallback {

        fun onTripsLoaded(Trips: List<Trip>)

        fun onDataNotAvailable()
    }

    interface GetTripCallback {

        fun onTripLoaded(trip: Trip)

        fun onDataNotAvailable()
    }

    fun getTrips(callback: LoadTripsCallback)

    fun getTrip(tripId: String, @NonNull callback: GetTripCallback)
}