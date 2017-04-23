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
    val timerStartValueInMilliSecs: Long = 20000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sendButton.onClick { sendSms() }

        chronometer1.base = SystemClock.elapsedRealtime() + timerStartValueInMilliSecs
        chronometer1.setOnChronometerTickListener { chronometerListener1() }
        timerButton1.onClick { startTimer1() }

        chronometer2.base = SystemClock.elapsedRealtime() + timerStartValueInMilliSecs
        chronometer2.setOnChronometerTickListener { chronometerListener2() }
        timerButton2.onClick { startTimer2() }
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

    fun startTimer1() {

        chronometer1.base = SystemClock.elapsedRealtime() + timerStartValueInMilliSecs
        chronometer1.start()
        timerButton1.text = "Reset"
    }

    fun startTimer2() {

        chronometer2.base = SystemClock.elapsedRealtime() + timerStartValueInMilliSecs
        chronometer2.start()
        timerButton2.text = "Reset"
    }

    fun chronometerListener1() {

        if (chronometer1.base < SystemClock.elapsedRealtime()) {

            chronometer1.stop()
            chronometer1.base = SystemClock.elapsedRealtime()
            timerButton1.text = "Start"
            toast("Timer1 expired!")
        }
    }
    fun chronometerListener2() {

        if (chronometer2.base < SystemClock.elapsedRealtime()) {

            chronometer2.stop()
            chronometer2.base = SystemClock.elapsedRealtime()
            timerButton2.text = "Start"
            toast("Timer2 expired!")
        }
    }
}
