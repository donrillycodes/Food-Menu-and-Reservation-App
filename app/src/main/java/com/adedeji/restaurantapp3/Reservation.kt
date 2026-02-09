package com.adedeji.restaurantapp3

import java.io.Serializable
import java.util.UUID

data class Reservation (
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val date: String,
    val guests: Int,
    val phoneNumber: String
) : Serializable