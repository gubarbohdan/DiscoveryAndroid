package com.example.gubar.discoveryandroid.clientlist

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.EditText
import com.example.gubar.discoveryandroid.R
import com.example.gubar.discoveryandroid.application.DiscoveryApplication
import com.example.gubar.discoveryandroid.data.Client
import com.example.gubar.discoveryandroid.repository.ClientRepository
import com.example.gubar.discoveryandroid.retrofit.DiscoveryApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * Created by bohdanhu on 26.01.18.
 */
class CreateClientDialog : DialogFragment() {
    init {
        injectDagger()
    }

    @Inject
    lateinit var discoveryApi: DiscoveryApi

    @Inject
    lateinit var clientRepository: ClientRepository

    private fun injectDagger() = DiscoveryApplication.appComponent.inject(this)


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        // Get the layout inflater
        val inflater = activity.layoutInflater

        val layout = inflater.inflate(R.layout.dialog_create_client, null)

        val firstName = layout.findViewById<EditText>(R.id.firstName)
        val lastName = layout.findViewById<EditText>(R.id.lastName)
        val email = layout.findViewById<EditText>(R.id.email)


        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(layout)
                // Add action buttons
                .setPositiveButton(R.string.dialog_create_ok_button, { dialog, id ->
                    val client = Client(firstName = firstName.text.toString(), lastName = lastName.text.toString(), email = email.text.toString())
                    discoveryApi.createClient(client).subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({client ->
                                clientRepository.getAllClients()
                            }, {t: Throwable? -> t!!.printStackTrace()})
                })
                .setNegativeButton(R.string.dialog_create_cancel_button, { dialog, id -> this@CreateClientDialog.getDialog().cancel() })
        return builder.create()
    }
}