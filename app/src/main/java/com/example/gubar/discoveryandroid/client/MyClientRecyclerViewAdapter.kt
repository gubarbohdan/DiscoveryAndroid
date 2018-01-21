package com.example.gubar.discoveryandroid.client

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.gubar.discoveryandroid.R

import com.example.gubar.discoveryandroid.client.ClientFragment.OnListFragmentInteractionListener

class MyClientRecyclerViewAdapter(private val mValues: List<Client>, private val mListener: OnListFragmentInteractionListener?) : RecyclerView.Adapter<MyClientRecyclerViewAdapter.ViewHolder>() {

    public override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_client, parent, false)
        return ViewHolder(view)
    }

    public override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues.get(position)
        holder.mIdView.text = mValues.get(position).id.toString()
        holder.mContentView.text = mValues.get(position).toString()

    }

    public override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView
        val mContentView: TextView
        var mItem: Client? = null

        init {
            mIdView = mView.findViewById(R.id.id)
            mContentView = mView.findViewById(R.id.content)
        }

        public override fun toString(): String {
            return super.toString() + " '" + mContentView.getText() + "'"
        }
    }
}
