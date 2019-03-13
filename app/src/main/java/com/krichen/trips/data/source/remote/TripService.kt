package com.krichen.trips.data.source.remote

import com.krichen.trips.data.Trip
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface TripService {
    @Headers("Accept: application/json")
    @GET("/trips")
    fun getTrips(): Observable<List<Trip>>

    @Headers("Accept: application/json")
    @GET("/trips/{id}")
    fun getTripById(@Path("id")  tripId:String): Observable<Trip>
}