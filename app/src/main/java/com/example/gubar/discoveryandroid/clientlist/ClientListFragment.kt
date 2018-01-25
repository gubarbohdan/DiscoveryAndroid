package com.example.gubar.discoveryandroid.clientlist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.gubar.discoveryandroid.R
import com.example.gubar.discoveryandroid.data.Client

/**
 * A fragment representing a list of Items.
 *
 *
 * Activities containing this fragment MUST implement the [OnListFragmentInteractionListener]
 * interface.
 */
/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
class ClientListFragment : Fragment() {
    // TODO: Customize parameters
    private var mColumnCount = 1
    private var mListener: OnListFragmentInteractionListener? = null
    private var mClientList: MutableList<Client> = mutableListOf()
    private var mAdapter: MyClientRecyclerViewAdapter? = null

    private var clientsListViewModel: ClientsListViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        if (arguments != null) {
            mColumnCount = arguments.getInt(ARG_COLUMN_COUNT)
        }
    }

    private fun initViewModel() {
        clientsListViewModel = ViewModelProviders.of(this).get(ClientsListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_client_list, container, false)

        mClientList.clear()

        val fab = view.findViewById<FloatingActionButton>(R.id.fab)

        fab.setOnClickListener { parentView ->
            Snackbar.make(parentView, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val list = view.findViewById<RecyclerView>(R.id.list)



        // Set the adapter
        if (list is RecyclerView) {
            val context = view.getContext()
            if (mColumnCount <= 1) {
                list.layoutManager = LinearLayoutManager(context)
            } else {
                list.layoutManager = GridLayoutManager(context, mColumnCount)
            }
        }
        initRecyclerView(list)
        getClients()


        return view
    }

    private fun initRecyclerView(list: RecyclerView) {
        mAdapter = MyClientRecyclerViewAdapter(mClientList, mListener)
        list.adapter = mAdapter
    }

    private fun getClients() {
        clientsListViewModel?.loadClients()?.observe(this, Observer { clients ->
            clients?.forEach{
                mClientList.add(it)
            }
            mAdapter?.notifyDataSetChanged()
        })
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnListFragmentInteractionListener")
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
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: Client)
    }

    companion object {

        // TODO: Customize parameter argument names
        private val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        fun newInstance(columnCount: Int): ClientListFragment {
            val fragment = ClientListFragment()
            val args = Bundle()
            args.putInt(ARG_COLUMN_COUNT, columnCount)
            fragment.arguments = args
            return fragment
        }
    }
}
