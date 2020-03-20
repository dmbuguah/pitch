package com.cdan.pitch.receiver

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import android.content.Context.TELEPHONY_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.telephony.TelephonyManager



private const val TAG = "PhoneStateReceiver"

class PhoneStateReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val tm = context.getSystemService(Service.TELEPHONY_SERVICE) as TelephonyManager
        when (tm.callState) {

            TelephonyManager.CALL_STATE_RINGING -> {
                val phoneNr = intent.getStringExtra("incoming_number")
                Toast.makeText(context, phoneNr, Toast.LENGTH_LONG).show()
            }
        }

    }
}