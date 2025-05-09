package pl.flashrow.navigation.destinations

import java.io.Serializable
import kotlin.time.Duration

data class ResultsNavArgs(
    val coolingTime: Duration
) : Serializable
