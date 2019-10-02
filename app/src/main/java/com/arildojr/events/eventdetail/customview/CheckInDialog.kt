package com.arildojr.events.eventdetail.customview

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.arildojr.data.event.dto.CheckInUserDTO
import com.arildojr.events.R
import com.arildojr.events.core.extension.clickWithDebounce
import com.arildojr.events.core.util.hideKeyboard
import com.arildojr.events.databinding.FragmentDialogCheckinBinding
import com.arildojr.events.eventdetail.viewmodel.EventDetailViewModel

class CheckInDialog : DialogFragment() {
    private lateinit var binding: FragmentDialogCheckinBinding

    private val viewModel: EventDetailViewModel by lazy {
        ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory()).get(
            EventDetailViewModel::class.java
        )
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentDialogCheckinBinding.inflate(LayoutInflater.from(context))
        binding.isLoading = false
        binding.requestResponse = false

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
        viewModel.checkInResult.observe(this, Observer { isSuccessful ->
            binding.isLoading = false
            binding.requestResponse = true
            binding.successful = isSuccessful
        })
    }

}