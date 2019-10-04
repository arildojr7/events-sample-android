package com.arildojr.events.eventdetail.customview

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import com.arildojr.data.event.dto.CheckInUserDTO
import com.arildojr.events.R
import com.arildojr.events.core.base.BaseDialogFragment
import com.arildojr.events.core.extension.clickWithDebounce
import com.arildojr.events.core.util.hideKeyboard
import com.arildojr.events.databinding.FragmentDialogCheckinBinding
import com.arildojr.events.eventdetail.viewmodel.EventDetailViewModel
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.sharedViewModel

class CheckInDialog : BaseDialogFragment() {
    private lateinit var binding: FragmentDialogCheckinBinding

    private val viewModel: EventDetailViewModel by sharedViewModel()

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

            launch {
                viewModel.checkIn(
                    CheckInUserDTO(
                        viewModel.event?.id,
                        binding.tieName.text.toString(),
                        binding.tieEmail.text.toString()
                    )
                )
            }
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