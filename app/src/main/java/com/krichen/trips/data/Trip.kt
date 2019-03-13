package com.krichen.trips.data

import android.databinding.BaseObservable
import android.databinding.Bindable

class Trip (val id: String) : BaseObservable() {
    val pilot:Pilot?=null
    val duration:Int?=0
    val pick_up:Place?=null
    val drop_off:Place?=null

}