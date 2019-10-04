package com.arildojr.events.core.customview

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.arildojr.events.R
import com.arildojr.events.databinding.FragmentDialogErrorBinding

class ErrorDialog(private val listener: ErrorListener) : DialogFragment() {
    private lateinit var binding: FragmentDialogErrorBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentDialogErrorBinding.inflate(LayoutInflater.from(context))
        isCancelable= false

        val alert = AlertDialog.Builder(activity, R.style.RoundedModal)
        alert.setView(binding.root)

        binding.btnRetry.setOnClickListener {
            listener.onClickRetry()
        }

        return alert.create()
    }

    interface ErrorListener {
        fun onClickRetry()
    }

}