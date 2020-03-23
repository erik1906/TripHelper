package com.eagledev.triphelper.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs

import com.eagledev.triphelper.R
import com.eagledev.triphelper.di.Injectable
import com.eagledev.triphelper.model.PassengerStatus
import com.eagledev.triphelper.utils.toCurrency
import com.eagledev.triphelper.utils.toFormat
import kotlinx.android.synthetic.main.history_detail_fragment.*
import timber.log.Timber

class HistoryDetailFragment : Fragment(), Injectable {

    private val args: HistoryDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.history_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.trip.let{
            tv_date.text = it.dateTime.toFormat()
            tv_estimated.text = it.tripInfo.price.toCurrency()
            tv_passenger_count.text = getProfit( it.passengers, it.currentPrice).toCurrency()
            Timber.tag("cycle").d("Id: ${it.id} passenger: ${it.passengers}")
            it.passengers?.forEach {passenger ->
                if(passenger.checked){
                    val layout = PassengerDetailLayout(view.context,passenger.name, it.currentPrice.toCurrency())
                    ll_passengers.addView(layout)
                }else{
                    val layout = PassengerDetailLayout(view.context,passenger.name, "00.00")
                    ll_passengers.addView(layout)
                }
            }

        }

    }

    private fun getProfit(list: List<PassengerStatus>?, price: Int): Int {
        var profit = 0
        list?.forEach {
            if(it.checked){
                profit += price
            }
        }
        return profit

    }
}
