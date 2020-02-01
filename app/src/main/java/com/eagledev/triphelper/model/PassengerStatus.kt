package com.eagledev.triphelper.model

import java.io.Serializable

data class PassengerStatus(
    val name: String,
    val checked: Boolean
): Serializable