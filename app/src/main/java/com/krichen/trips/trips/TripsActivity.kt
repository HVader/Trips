package com.krichen.trips.trips

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.krichen.trips.R
import com.krichen.trips.data.Trip
import com.krichen.trips.data.source.TripsRepository
import com.krichen.trips.databinding.ActivityTripsBinding
import com.krichen.trips.tripdetail.TripDetailActivity
import com.krichen.trips.tripdetail.TripDetailViewModel
import com.krichen.trips.utils.Event
import com.krichen.trips.utils.obtainViewModel
import kotlinx.android.synthetic.main.activity_trips.*

class TripsActivity : AppCompatActivity() {


    private lateinit var tripsViewModelFactory: TripsViewModelFactory

    private lateinit var viewModel: TripsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityTripsBinding = DataBindingUtil.setContentView(this,R.layout.activity_trips)
        binding.lifecycleOwner = this


        tripsViewModelFactory = TripsViewModelFactory(application, TripsRepository())




        viewModel = obtainViewModel().apply{
            openTripEvent.observe(this@TripsActivity, Observer<Event<String>> { event ->
                event!!.getContentIfNotHandled()?.let {
                    openTripDetails(it)
                }
            })
        }

        binding.viewModel=viewModel
        viewModel.start()

        setRecyclerView()

    }

    private fun setRecyclerView(){
        rvTrips.adapter = TripsAdapter(viewModel)
        val layoutManager = LinearLayoutManager(this)
        rvTrips.layoutManager = layoutManager
        val dividerItemDecoration = DividerItemDecoration(rvTrips.context,
                layoutManager.orientation)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dividerItemDecoration.setDrawable(getDrawable(R.drawable.divider)!!)
        }
        rvTrips.addItemDecoration(dividerItemDecoration)
    }


    private fun openTripDetails(tripId: String) {
        val intent = Intent(this, TripDetailActivity::class.java).apply {
            putExtra(TripDetailActivity.EXTRA_TRIP_ID, tripId)
        }
        startActivity(intent)
    }

    private fun obtainViewModel(): TripsViewModel = obtainViewModel(TripsViewModel::class.java)
}