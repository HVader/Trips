package com.krichen.trips.trips

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.krichen.trips.data.source.TripsRepository

@Suppress("UNCHECKED_CAST")
class TripsViewModelFactory (private val context: Application, private val repository: TripsRepository) :
        ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TripsViewModel(context, repository) as T
    }
}