package com.adedeji.restaurantapp3

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.adedeji.restaurantapp3.databinding.FragmentReservationFormBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ReservationFormFragment : Fragment() {
    private lateinit var binding: FragmentReservationFormBinding
    private val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReservationFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupDatePicker()
        setupContinueButton()
    }

    private fun setupDatePicker() {
        binding.reservationDate.setOnClickListener {
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, selectedYear, selectedMonth, selectedDay ->
                    calendar.set(Calendar.YEAR, selectedYear)
                    calendar.set(Calendar.MONTH, selectedMonth)
                    calendar.set(Calendar.DAY_OF_MONTH, selectedDay)

                    val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
                    binding.reservationDate.setText(dateFormat.format(calendar.time))
                },
                year, month, day
            )

            // Set minimum date to today
            datePickerDialog.datePicker.minDate = System.currentTimeMillis()
            datePickerDialog.show()
        }
    }

    private fun setupContinueButton() {
        binding.btnContinue.setOnClickListener {
            if (validateInputs()) {
                val reservation = Reservation(
                    name = binding.fullNameReservation.text.toString(),
                    date = binding.reservationDate.text.toString(),
                    guests = binding.reservationGuests.text.toString().toIntOrNull() ?: 1,
                    phoneNumber = binding.reservationPhoneNumber.text.toString()
                )
                (activity as ContactActivity).showReservationPreview(reservation)
            }
        }
    }

    //null checks
    private fun validateInputs(): Boolean {
        var isValid = true

        if (binding.fullNameReservation.text.isNullOrEmpty()) {
            binding.fullNameReservation.error = "Please enter your name"
            isValid = false
        }

        if (binding.reservationDate.text.isNullOrEmpty()) {
            binding.reservationDate.error = "Please select a date"
            isValid = false
        }

        if (binding.reservationGuests.text.isNullOrEmpty()) {
            binding.reservationGuests.error = "Please enter number of guests"
            isValid = false
        } else if (binding.reservationGuests.text.toString().toIntOrNull() ?: 0 < 1) {
            binding.reservationGuests.error = "Must be at least 1 guest"
            isValid = false
        }

        if (binding.reservationPhoneNumber.text.isNullOrEmpty()) {
            binding.reservationPhoneNumber.error = "Please enter phone number"
            isValid = false
        }

        return isValid
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}
