package com.eagledev.triphelper.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.eagledev.triphelper.R
import com.eagledev.triphelper.di.Injectable
import com.eagledev.triphelper.utils.ViewModelFactory
import kotlinx.android.synthetic.main.history_fragment.*
import timber.log.Timber
import javax.inject.Inject

class HistoryFragment : Fragment(), Injectable {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: HistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.history_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HistoryViewModel::class.java)

        val adapter = TripAdapter{
            val directions = HistoryFragmentDirections.actionHistoryFragmentToHistoryDetail(it)
            findNavController().navigate(directions)
        }

        rv_history.adapter = adapter

        viewModel.tripList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }
}
