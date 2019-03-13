package com.krichen.trips.trips

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.krichen.trips.data.Trip
import com.krichen.trips.data.source.TripsDataSource
import com.krichen.trips.data.source.TripsRepository
import com.krichen.trips.utils.Event


class TripsViewModel(
        context: Application,
        private val tripsRepository: TripsRepository
) : AndroidViewModel(context) {

    private val _items = MutableLiveData<List<Trip>>().apply { value = emptyList() }

    val items: LiveData<List<Trip>>
    get() = _items


    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean>
        get() = _dataLoading

    private val _openTripEvent = MutableLiveData<Event<String>>()
    val openTripEvent: LiveData<Event<String>>
        get() = _openTripEvent


    fun start() {
        loadTrips(false)
    }

    fun getTripAt(position:Int):Trip?{
        return items.value?.get(position)
    }


    private fun loadTrips( showLoadingUI: Boolean) {
        if (showLoadingUI) {
            _dataLoading.value = true
        }
        tripsRepository.getTrips(object : TripsDataSource.LoadTripsCallback {
            override fun onTripsLoaded(Trips: List<Trip>) {
                _items.value = Trips
            }

            override fun onDataNotAvailable() {

            }
        })
    }

    internal fun openTrip(tripId: String) {
        _openTripEvent.value = Event(tripId)
    }


}