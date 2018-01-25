package com.example.gubar.discoveryandroid.client

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.gubar.discoveryandroid.R
import com.example.gubar.discoveryandroid.clientlist.MyClientRecyclerViewAdapter
import com.example.gubar.discoveryandroid.data.Client
import com.example.gubar.discoveryandroid.data.Trip
import kotlinx.android.synthetic.main.fragment_client.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ClientFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ClientFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ClientFragment : Fragment() {

    private var mClientsListViewModel: ClientViewModel? = null

    // TODO: Rename and change types of parameters
    private var mClientId: Long = 0

    private var mClient: Client? = null

    private var mClientTripList: MutableList<Trip> = mutableListOf()

    private var mAdapter: MyClientTripsRecyclerViewAdapter? = null
    private var mListener: OnFragmentInteractionListener? = null

    private lateinit var mFirstNameTextView: TextView
    private lateinit var mLastNameTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mClientId = arguments.getLong(CLIENT_ID)
        }
        initViewModel()
    }

    private fun initViewModel() {
        mClientsListViewModel = ViewModelProviders.of(this).get(ClientViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_client, container, false)


        mFirstNameTextView = view.findViewById<TextView>(R.id.firstName)
        mLastNameTextView = view.findViewById<TextView>(R.id.lastName)

        setView()

        val list = view.findViewById<RecyclerView>(R.id.clientTripsRecycler)



        // Set the adapter
        if (list is RecyclerView) {
            val context = view.getContext()
            list.layoutManager = LinearLayoutManager(context)
        }
        initRecyclerView(list)
        getClient()

        return view
    }

    private fun setView() {
        mFirstNameTextView.text = mClient?.firstName
        mLastNameTextView.text = mClient?.lastName
    }

    private fun initRecyclerView(list: RecyclerView) {
        mAdapter = MyClientTripsRecyclerViewAdapter(mClientTripList, mListener)
        list.adapter = mAdapter
    }

    private fun getClient() {
        mClientsListViewModel?.getClient(mClientId)?.observe(this, Observer { client ->
            mClient = client
            setView()
            client?.trips?.forEach{
                mClientTripList.add(it)
            }
            mAdapter?.notifyDataSetChanged();
        })
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val CLIENT_ID = "mClientId"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ClientFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(clientId: Long): ClientFragment {
            val fragment = ClientFragment()
            val args = Bundle()
            args.putLong(CLIENT_ID, clientId)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
