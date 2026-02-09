package com.adedeji.restaurantapp3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.adedeji.restaurantapp3.databinding.FragmentReservationPreviewBinding

class ReservationPreviewFragment : Fragment() {
    private lateinit var binding: FragmentReservationPreviewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReservationPreviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val reservation = try {
            arguments?.getSerializable("reservation") as? Reservation
        } catch (e: Exception) {
            null
        }

        
        if (reservation == null) {
            Log.e("ReservationPreview", "Reservation is null, cannot proceed")
            activity?.finish() // Close activity if reservation is invalid
            return
        }

        Log.d("ReservationPreview", "Received reservation: $reservation")

        binding.apply {
            reservationNameInput.text = reservation.name
            reservationDateInput.text = "Date: ${reservation.date}"
            reservationGuestsInput.text = "Guests: ${reservation.guests}"
            reservationPhoneInput.text = "Phone: ${reservation.phoneNumber}"

            confirmReservationButton.setOnClickListener {
                try {
                    val intent = Intent(requireContext(), ProfileActivity::class.java).apply {
                        putExtra("Reservation", reservation)
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    }
                    Log.d("ReservationPreview", "Starting ProfileActivity with reservation: $reservation")
                    startActivity(intent)
                    activity?.finish() // Finish after starting new activity
                } catch (e: Exception) {
                    Log.e("ReservationPreview", "Failed to start ProfileActivity: ${e.message}", e)

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}