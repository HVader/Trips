package com.krichen.trips.tripdetail

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.krichen.trips.R
import com.krichen.trips.data.source.TripsRepository
import com.krichen.trips.databinding.ActivityTripDetailBinding
import com.krichen.trips.utils.obtainViewModel

class TripDetailActivity : AppCompatActivity() {

    private lateinit var tripsViewModelFactory: TripDetailViewModelFactory
    private lateinit var viewModel: TripDetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityTripDetailBinding =DataBindingUtil.setContentView(this,R.layout.activity_trip_detail)
        supportActionBar!!.hide()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.lifecycleOwner=this
        tripsViewModelFactory = TripDetailViewModelFactory(application, TripsRepository())
        viewModel = obtainViewModel()
        binding.modelview=viewModel
        viewModel.start(intent.getStringExtra(TripDetailActivity.EXTRA_TRIP_ID))



    }

    private fun obtainViewModel(): TripDetailViewModel = obtainViewModel(TripDetailViewModel::class.java)

    companion object {

        const val EXTRA_TRIP_ID = "TRIP_ID"

    }
}
