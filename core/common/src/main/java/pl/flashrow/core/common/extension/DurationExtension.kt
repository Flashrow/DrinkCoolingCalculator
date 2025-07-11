package pl.flashrow.core.common.extension

import kotlin.time.Duration

fun Duration.toHourSecondsText(): String {
    val hours = this.inWholeHours
    val minutes = this.inWholeMinutes % 60
    val seconds = this.inWholeSeconds % 60
    return String.format("%02dh %02dm %02ds", hours, minutes, seconds)
}