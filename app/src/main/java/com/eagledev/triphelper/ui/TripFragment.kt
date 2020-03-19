package com.eagledev.triphelper.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.eagledev.triphelper.R
import com.eagledev.triphelper.di.Injectable
import com.eagledev.triphelper.di.InjectingSavedStateViewModelFactory
import com.eagledev.triphelper.model.Passenger
import com.eagledev.triphelper.model.Trip
import com.eagledev.triphelper.model.TripInfo
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.edit_text_layout.view.*
import kotlinx.android.synthetic.main.trip_fragment.*
import timber.log.Timber
import javax.inject.Inject

class TripFragment : Fragment(), Injectable {

    private lateinit var viewModel: TripViewModel

    @Inject
    lateinit var abstractFactory: InjectingSavedStateViewModelFactory

    private val passengerList: ArrayList<PassengerLayout> = arrayListOf()

    private var name = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.trip_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModelFactory = abstractFactory.create(this)
        viewModel = ViewModelProviders.of( this, viewModelFactory).get(TripViewModel::class.java)

        viewModel.getDate().observe(viewLifecycleOwner, Observer {
            tv_date.text = it
        })

        viewModel.inTrip().observe(viewLifecycleOwner, Observer {
            if(it){
                group_active.visibility = View.VISIBLE
                button_new_trip.visibility = View.GONE


            }else{
                group_active.visibility = View.GONE
                button_new_trip.visibility = View.VISIBLE
            }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {error->
                if(error){
                    Snackbar.make(activity!!.findViewById(android.R.id.content), "No tienes mas asientos disponibles", Snackbar.LENGTH_LONG).show()
                }else{
                    addPassenger(view, name)
                }
            }
        })
        viewModel.getPassenger().observe(viewLifecycleOwner, Observer {
            passengerList.clear()
            ll_passengers.removeAllViews()
            it.forEach { passengerStatus ->
                addPassengerSimple(view, passengerStatus.name, passengerStatus.checked)
            }

        })
        button_new_trip.setOnClickListener {
            viewModel.setTrip(true)
        }

        b_finish_trip.setOnClickListener {

            val passengerStatus = passengerList.map {
                it.toPassengerStatus()
            }
            viewModel.addPassenger(passengerStatus)
            viewModel.saveTrip(false)
            viewModel.clearTrip()
            passengerList.clear()
            ll_passengers.removeAllViews()
        }
        floatingActionButton.setOnClickListener {
            getPassengerInfo(view)
        }

        viewModel.getPassengerCount().observe(viewLifecycleOwner, Observer {
            tv_estimated.text = it.price.toString()
            tv_pssenger_count.text = it.count.toString()
        })



    }

    override fun onResume() {
        super.onResume()
        viewModel.start()
    }

    private fun getPassengerInfo(view: View) {
        val builder = AlertDialog.Builder(view.context)
        builder.setTitle("Agrega al pasajero: ")

        val editTextLayout = LayoutInflater.from(view.context).inflate(R.layout.edit_text_layout, null)
        builder.setView(editTextLayout)

        builder.setPositiveButton("Agregar"
        ) { dialog, _ ->
            Timber.tag("settdebug").d("Settings Seat alert ")
            name = editTextLayout.editText.text.toString()
            viewModel.addPassenger()
            dialog.dismiss()
        }
        // create and show the alert dialog
        val dialog = builder.create()
        dialog.show()
    }

    private fun addPassenger(view: View, name: String) {
        val passengerLayout = PassengerLayout(view.context, name)
        passengerList.add(passengerLayout)
        ll_passengers.addView(passengerLayout)

    }

    private fun addPassengerSimple(view: View, name: String, checked: Boolean) {

        val passengerLayout = PassengerLayout(view.context, name)
        passengerLayout.setCheck(checked)
        passengerList.add(passengerLayout)
        ll_passengers.addView(passengerLayout)

    }

    override fun onPause() {
        super.onPause()
        val passengerStatus = passengerList.map {
            it.toPassengerStatus()
        }
        ll_passengers.removeAllViews()
        passengerList.clear()
        viewModel.addPassenger(passengerStatus)

    }

    override fun onStop() {
        super.onStop()
        viewModel.saveTrip(true)
    }

}
