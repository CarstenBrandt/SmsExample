package cat.smsexample

import android.app.Activity
import android.os.Bundle
import android.os.SystemClock
import android.telephony.SmsManager
import android.widget.Chronometer
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.toast

class MainActivity : Activity() {
    val timerStartValue: Long = 20000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sendButton.onClick { sendSms() }

        chronometer.base = SystemClock.elapsedRealtime() + timerStartValue
        chronometer.setOnChronometerTickListener { chronometerListener() }
        timerButton.onClick { startTimer() }
    }

    fun sendSms() {

        val phoneNumber: String = "4521210752"
        val message: String = "12345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                "12345678901234567890123456789012345678901234567890123456789012345678901234567890TEST"

        val sms: SmsManager = SmsManager.getDefault()

        var messageParts: ArrayList<String> = sms.divideMessage(message)

        for (item in messageParts) {
            toast(item.toString())
        }

//        sms.sendMultipartTextMessage(phoneNumber, null, messageParts, null, null)

        toast("Message sent")
    }

    fun startTimer() {

        chronometer.base = SystemClock.elapsedRealtime() + timerStartValue
        chronometer.start()
        timerButton.text = "Reset"
    }

    fun chronometerListener() {

        if (chronometer.base < SystemClock.elapsedRealtime()) {

            chronometer.stop()
            timerButton.text = "Start"
            toast("Timer expired!")
        }
    }
}
