package com.buidanhtuan.saveme.view.adapter

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.widget.Toast
import com.buidanhtuan.saveme.R

class TimeAdapter :  BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "Alarm! Wake up! Wake up!", Toast.LENGTH_LONG).show()
        var mediaPlayer : MediaPlayer? = null
        mediaPlayer = MediaPlayer.create(context, R.raw.a)
        mediaPlayer?.run {
            start()
        }
    }
}