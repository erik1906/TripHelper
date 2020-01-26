package com.eagledev.triphelper.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.eagledev.triphelper.R
import com.eagledev.triphelper.di.Injectable
import com.eagledev.triphelper.utils.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.settings_fragment.*
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
        initObservers()

        b_save.setOnClickListener {
            viewModel.updateSetting(number_picker.value, et_price.text.toString())
        }
    }

    private fun initObservers() {
        viewModel.price.observe(viewLifecycleOwner, Observer {
            et_price.setText(it.toString())
        })

        viewModel.seats.observe(viewLifecycleOwner, Observer {
            number_picker.value = it
        })

        viewModel.updateStatus.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {success->
                if(success){
                    Snackbar.make(activity!!.findViewById(android.R.id.content), "Configuracion guardada correctamente.", Snackbar.LENGTH_LONG).show()
                }else{
                    Snackbar.make(activity!!.findViewById(android.R.id.content), "Error guardando la configuracion.", Snackbar.LENGTH_LONG).show()
                }
            }
        })

        viewModel.empty.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {error->
                if(error){
                    Snackbar.make(activity!!.findViewById(android.R.id.content), "Favor de ingresar un precio valido.", Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }
}
