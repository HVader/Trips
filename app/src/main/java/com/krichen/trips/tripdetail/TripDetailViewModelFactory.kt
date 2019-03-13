package com.krichen.trips.tripdetail

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.krichen.trips.data.source.TripsRepository

@Suppress("UNCHECKED_CAST")
class TripDetailViewModelFactory (private val context: Application, private val repository: TripsRepository) :
        ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TripDetailViewModelFactory(context, repository) as T
    }
}