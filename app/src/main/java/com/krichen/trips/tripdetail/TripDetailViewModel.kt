package com.krichen.trips.tripdetail

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.krichen.trips.data.Trip
import com.krichen.trips.data.source.TripsDataSource
import com.krichen.trips.data.source.TripsRepository

class TripDetailViewModel(
        context: Application,
        private val tripsRepository: TripsRepository)
    :AndroidViewModel(context),TripsDataSource.GetTripCallback{


    private val _trip = MutableLiveData<Trip>()

    val trip: MutableLiveData<Trip>
        get() = _trip

    private val tripId: String?
        get() = _trip.value?.id

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean>
        get() = _dataLoading

    private val _isDataAvailable = MutableLiveData<Boolean>()
    val isDataAvailable: LiveData<Boolean>
        get() = _isDataAvailable

    fun start(tripId: String?) {
        if (tripId != null) {
            _dataLoading.value = true
            tripsRepository.getTrip(tripId, this)
        }
    }

    private fun setTrip(trip: Trip?) {
        this._trip.value = trip
        _isDataAvailable.value = trip != null
    }

    override fun onTripLoaded(trip: Trip) {
        setTrip(trip)
        Log.d(TAG,"onTripLoaded() ${trip.pilot!!.name}")
    }

    override fun onDataNotAvailable() {
        _trip.value = null
        _dataLoading.value = false
        _isDataAvailable.value = false
        Log.d(TAG,"onDataNotAvailable()")
    }

    companion object {
        const val TAG:String="TripDetailViewModel"
    }
}
