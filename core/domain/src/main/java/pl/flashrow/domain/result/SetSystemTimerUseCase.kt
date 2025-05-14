package pl.flashrow.domain.result

import android.content.Context
import android.content.Intent
import android.provider.AlarmClock
import javax.inject.Inject

class SetSystemTimerUseCase @Inject constructor() {
    operator fun invoke(context: Context, durationSeconds: Int, message: String? = null) {
        val intent = Intent(AlarmClock.ACTION_SET_TIMER).apply {
            putExtra(AlarmClock.EXTRA_LENGTH, durationSeconds)
            if (message != null) {
                putExtra(AlarmClock.EXTRA_MESSAGE, message)
            }
        }
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }
}