package com.cdan.pitch.receiver

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import android.content.Context.TELEPHONY_SERVICE
import android.media.AudioManager
import androidx.core.content.ContextCompat.getSystemService
import android.telephony.TelephonyManager
import androidx.core.content.ContextCompat.getSystemService


private const val TAG = "PhoneStateReceiver"

class PhoneStateReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val tm = context.getSystemService(Service.TELEPHONY_SERVICE) as TelephonyManager
        when (tm.callState) {

            TelephonyManager.CALL_STATE_RINGING -> {
                val phoneNr:String = intent.getStringExtra("incoming_number")
                Log.e(TAG, "Call before ringing");
                Toast.makeText(context, phoneNr, Toast.LENGTH_LONG).show()
                if (phoneNr != null && phoneNr.equals("0700000000")) {
                    Log.e(TAG, "Call after ringing");
                    Toast.makeText(context, phoneNr, Toast.LENGTH_LONG).show()
                    val mobilemode =
                        context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
                    mobilemode.setStreamVolume(
                        AudioManager.STREAM_RING, mobilemode.getStreamMaxVolume(AudioManager.STREAM_RING),
                        AudioManager.FLAG_ALLOW_RINGER_MODES or AudioManager.FLAG_PLAY_SOUND);

                }
            }
        }

    }
}