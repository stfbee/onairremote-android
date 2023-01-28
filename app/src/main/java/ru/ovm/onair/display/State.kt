package ru.ovm.onair.display

import kotlinx.serialization.Serializable

@Serializable
data class State(
    val onAir: Boolean,
)