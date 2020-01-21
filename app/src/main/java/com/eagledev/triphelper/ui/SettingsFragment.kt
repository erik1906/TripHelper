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

class SettingsFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.settings_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SettingsViewModel::class.java)

    }
}
