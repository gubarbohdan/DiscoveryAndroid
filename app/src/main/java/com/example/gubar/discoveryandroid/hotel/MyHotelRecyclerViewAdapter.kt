package com.example.gubar.discoveryandroid.hotel

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.gubar.discoveryandroid.R
import com.example.gubar.discoveryandroid.data.Hotel

import com.example.gubar.discoveryandroid.hotel.HotelFragment.OnListFragmentInteractionListener

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyHotelRecyclerViewAdapter(private val mValues: List<Hotel>, private val mListener: OnListFragmentInteractionListener?) : RecyclerView.Adapter<MyHotelRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_hotel, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mIdView.text = mValues[position].id.toString()
        holder.mContentView.text = mValues[position].name

    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView
        val mContentView: TextView

        init {
            mIdView = mView.findViewById<TextView>(R.id.id) as TextView
            mContentView = mView.findViewById<TextView>(R.id.content) as TextView
        }

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
