package com.example.gubar.discoveryandroid.clientlist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.gubar.discoveryandroid.R

import com.example.gubar.discoveryandroid.clientlist.ClientListFragment.OnListFragmentInteractionListener
import com.example.gubar.discoveryandroid.data.Client

class MyClientRecyclerViewAdapter(private val mValues: List<Client>?, private val mListener: OnListFragmentInteractionListener?) : RecyclerView.Adapter<MyClientRecyclerViewAdapter.ViewHolder>() {

    public override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_client_list, parent, false)
        return ViewHolder(view)
    }

    public override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues?.get(position)
        holder.mIdView.text = mValues?.get(position)?.id.toString()
        holder.mFirstNameView.text = mValues?.get(position)?.firstName
        holder.mLastName.text = mValues?.get(position)?.lastName
        holder.mClientRow.setOnClickListener{
            mListener?.onListFragmentInteraction(holder.mItem!!)
        }
    }

    public override fun getItemCount(): Int {
        return mValues?.size?:0
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mClientRow: LinearLayout
        val mIdView: TextView
        val mFirstNameView: TextView
        val mLastName: TextView
        var mItem: Client? = null

        init {
            mClientRow = mView.findViewById(R.id.clientRow)
            mIdView = mView.findViewById(R.id.id)
            mFirstNameView = mView.findViewById(R.id.firstName)
            mLastName = mView.findViewById(R.id.lastName)
        }

        public override fun toString(): String {
            return super.toString() + " '" + mFirstNameView.getText() + "'"
        }
    }
}
