package com.arildojr.events.core.customview

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.arildojr.events.R
import com.arildojr.events.databinding.FragmentDialogNoInternetBinding

class NoInternetDialog : DialogFragment() {
    private lateinit var binding: FragmentDialogNoInternetBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentDialogNoInternetBinding.inflate(LayoutInflater.from(context))

        val alert = AlertDialog.Builder(activity, R.style.RoundedModal)
        alert.setView(binding.root)

        return alert.create()
    }

}