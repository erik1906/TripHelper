package com.eagledev.triphelper.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eagledev.triphelper.R
import com.eagledev.triphelper.model.Trip
import com.eagledev.triphelper.utils.toFormat
import kotlinx.android.synthetic.main.history_card.view.*

class TripAdapter(private val listener: (Trip) -> Unit) : PagedListAdapter<Trip, TripAdapter.TripViewHolder>(DIFf_CALLBACK){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
       return TripViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        val trip = getItem(position)
        if(trip != null){
            holder.bind(trip, listener)
        }

    }


    companion object{
        private val DIFf_CALLBACK = object: DiffUtil.ItemCallback<Trip>(){
            override fun areItemsTheSame(oldItem: Trip, newItem: Trip): Boolean =
                oldItem == newItem


            override fun areContentsTheSame(oldItem: Trip, newItem: Trip): Boolean =
                oldItem.id == newItem.id
        }
    }


    class TripViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

        companion object{
            fun create(parent: ViewGroup): TripViewHolder{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.history_card, parent, false)
                return TripViewHolder(view)
            }
        }
        private var trip: Trip? = null

        fun bind(trip: Trip, listener: (Trip) -> Unit){
            this.trip = trip

            view.setOnClickListener {
                listener(trip)
            }

            view.tv_date.text = trip.dateTime.toFormat()
            view.tv_passengers.text = view.context.getString(R.string.passengers, trip.tripInfo.count)
        }

    }
}