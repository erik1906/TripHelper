package com.eagledev.triphelper.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import com.eagledev.triphelper.R
import com.eagledev.triphelper.model.PassengerStatus
import kotlinx.android.synthetic.main.passener_layout.view.*

class PassengerLayout (context: Context): LinearLayout(context){

    private var name: String = ""

    constructor(context: Context, text: String): this(context){
        checkBox.text = text
        name = text
    }

    init {

        LayoutInflater.from(getContext()).inflate(R.layout.passener_layout, this)
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    fun isChecked() = checkBox.isChecked

    fun toPassengerStatus() = PassengerStatus(name, checkBox.isChecked)

    fun setCheck(checked: Boolean){
        checkBox.isChecked = checked
    }
}