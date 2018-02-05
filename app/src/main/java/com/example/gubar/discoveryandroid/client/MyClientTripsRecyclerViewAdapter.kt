package com.example.gubar.discoveryandroid.client

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.gubar.discoveryandroid.R
import com.example.gubar.discoveryandroid.clientlist.ClientListFragment
import com.example.gubar.discoveryandroid.data.Client
import com.example.gubar.discoveryandroid.data.Trip
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by bohdanhu on 25.01.18.
 */
class MyClientTripsRecyclerViewAdapter(private val mValues: List<Trip>?, private val mListener: ClientFragment.OnFragmentInteractionListener?) : RecyclerView.Adapter<MyClientTripsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_client_trip_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mValues?.size?:0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues?.get(position)
        holder.mFromDateView.text = SimpleDateFormat("dd/MM/yyyy").format(Date(mValues?.get(position)?.fromDate?:System.currentTimeMillis()))
        holder.mToDateView.text = SimpleDateFormat("dd/MM/yyyy").format(Date(mValues?.get(position)?.toDate?:System.currentTimeMillis()))
        holder.mPriceView.text = mValues?.get(position)?.price.toString()
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mFromDateView: TextView
        val mToDateView: TextView
        val mPriceView: TextView
        var mItem: Trip? = null

        init {
            mFromDateView = mView.findViewById(R.id.fromDate)
            mToDateView = mView.findViewById(R.id.toDate)
            mPriceView = mView.findViewById(R.id.price)
        }

        public override fun toString(): String {
            return super.toString() + " '" + mPriceView.getText() + "'"
        }
    }
}