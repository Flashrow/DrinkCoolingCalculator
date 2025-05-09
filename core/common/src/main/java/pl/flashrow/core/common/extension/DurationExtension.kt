package pl.flashrow.core.common.extension

import kotlin.time.Duration

/**
 * Konwertuje obiekt Duration na string w formacie "hh:mm"
 */
fun Duration.toHhMm(): String {
    val hours = this.inWholeHours
    val minutes = this.inWholeMinutes % 60
    return String.format("%02d:%02d", hours, minutes)
}