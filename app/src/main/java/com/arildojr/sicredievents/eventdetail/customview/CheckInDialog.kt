package com.arildojr.sicredievents.eventdetail.customview

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.arildojr.data.event.dto.CheckInUserDTO
import com.arildojr.sicredievents.R
import com.arildojr.sicredievents.core.extension.clickWithDebounce
import com.arildojr.sicredievents.core.util.hideKeyboard
import com.arildojr.sicredievents.databinding.FragmentDialogCheckinBinding
import com.arildojr.sicredievents.eventdetail.viewmodel.EventDetailViewModel

class CheckInDialog : DialogFragment() {
    private lateinit var binding: FragmentDialogCheckinBinding

    private val viewModel: EventDetailViewModel by lazy {
        ViewModelProvider(activity!!, ViewModelProvider.NewInstanceFactory()).get(
            EventDetailViewModel::class.java
        )
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentDialogCheckinBinding.inflate(LayoutInflater.from(context))
        binding.isLoading = false

        subscribeUi()
        setupListeners()

        val alert = AlertDialog.Builder(activity, R.style.RoundedModal)
        alert.setView(binding.root)

        return alert.create()
    }

    private fun setupListeners() {
        binding.btnCheckInFragment.clickWithDebounce {
            hideKeyboard(binding.root)
            binding.isLoading = true

            viewModel.checkIn(
                CheckInUserDTO(
                    viewModel.event?.id,
                    binding.tieName.text.toString(),
                    binding.tieEmail.text.toString()
                )
            )
        }
    }

    private fun subscribeUi() {
        viewModel.checkInIsSuccessful.observe(this, Observer {
            binding.isLoading = false
        })
    }

}