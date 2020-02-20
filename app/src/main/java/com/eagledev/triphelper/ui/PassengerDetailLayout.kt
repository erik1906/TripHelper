package com.eagledev.triphelper.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import com.eagledev.triphelper.R
import kotlinx.android.synthetic.main.passener_detail.view.*


class PassengerDetailLayout (context: Context): LinearLayout(context){

    constructor(context: Context, name: String, price: String): this(context){

        tv_name.text = name
        tv_price.text = price
    }

    init {

        LayoutInflater.from(getContext()).inflate(R.layout.passener_detail, this)
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

}