package com.krichen.trips.data.source

import android.util.Log
import com.krichen.trips.data.source.remote.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TripsRepository:TripsDataSource{


    override fun getTrips(callback: TripsDataSource.LoadTripsCallback) {
        val observable=ApiClient.getClient().getTrips()

        val subscribe = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { trips ->
                            callback.onTripsLoaded(trips)
                        },
                        { error ->
                            Log.e(TAG, error.toString())
                        }
                )

    }



    override fun getTrip(tripId: String, callback: TripsDataSource.GetTripCallback) {
            val observable=ApiClient.getClient().getTripById(tripId)

            val subscribe = observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { trip ->
                                callback.onTripLoaded(trip)
                            },
                            { error ->
                                Log.e(TAG, error.toString())
                            }
                    )

        }
        companion object {
            private const val TAG= "TripsRepository"
        }
    }


