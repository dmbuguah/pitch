package com.cdan.pitch.receiver

import android.app.NotificationManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import android.content.Context.TELEPHONY_SERVICE
import android.media.AudioManager
import android.os.Build
import android.telephony.TelephonyManager
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.*


private const val TAG = "PhoneStateReceiver"

class PhoneStateReceiver : BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context, intent: Intent) {
        val tm = context.getSystemService(Service.TELEPHONY_SERVICE) as TelephonyManager
        when (tm.callState) {

            TelephonyManager.CALL_STATE_RINGING -> {
                val phoneNr:String = intent.getStringExtra("incoming_number")
                val notificationManager = context.getSystemService(
                    Context.NOTIFICATION_SERVICE) as NotificationManager

                if (!notificationManager.isNotificationPolicyAccessGranted) {

                    val notint = Intent(
                        android.provider.Settings
                            .ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
                    notint.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(context, notint, null);
                }
                if (phoneNr.equals("0700000000")) {
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