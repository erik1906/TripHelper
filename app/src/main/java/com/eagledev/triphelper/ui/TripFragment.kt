package com.eagledev.triphelper.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eagledev.triphelper.R
import com.eagledev.triphelper.di.Injectable
import com.eagledev.triphelper.utils.ViewModelFactory
import javax.inject.Inject

class TripFragment : Fragment(), Injectable {


    private lateinit var viewModel: TripViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.trip_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of( this, viewModelFactory).get(TripViewModel::class.java)

    }
}
