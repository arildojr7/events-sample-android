package com.arildojr.sicredievents.eventdetail.customview

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.arildojr.sicredievents.R

class CheckInDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view: View? = activity?.layoutInflater?.inflate(R.layout.fragment_dialog_checkin, null)

        val alert = AlertDialog.Builder(activity, R.style.RoundedModal)
        alert.setView(view)

        return alert.create()
    }
}