package com.buidanhtuan.saveme.view.activity

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TimePicker
import android.widget.Toast
import android.widget.ToggleButton
import androidx.annotation.RequiresApi
import com.buidanhtuan.saveme.R
import com.buidanhtuan.saveme.view.adapter.TimeAdapter
import java.util.*

class TimeActivity : AppCompatActivity() {
    var alarmTimePicker: TimePicker? = null
    var pendingIntent: PendingIntent? = null
    var alarmManager: AlarmManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)
        alarmTimePicker = findViewById<TimePicker>(R.id.timePicker)
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
    }
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun onToggleClicked(view: View) {
        startAlarm(view)
    }

    private fun startAlarm(view: View) {
        if ((view as ToggleButton).isChecked) {
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker!!.currentHour)
            calendar.set(Calendar.MINUTE, alarmTimePicker!!.currentMinute)
            val intent = Intent(this, TimeAdapter::class.java)
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)

            var time = calendar.timeInMillis - calendar.timeInMillis % 60000

            if (System.currentTimeMillis() > time) {
                if (Calendar.AM_PM === 0)
                    time += 1000 * 60 * 60 * 12
                else
                    time += time + 1000 * 60 * 60 * 24
            }
            /* For Repeating Alarm set time intervals as 10000 like below lines */
            // alarmManager!!.setRepeating(AlarmManager.RTC_WAKEUP, time, 10000, pendingIntent)

            alarmManager!!.set(AlarmManager.RTC, time, pendingIntent);
            Toast.makeText(this, "ALARM ON", Toast.LENGTH_SHORT).show()
        } else {
            alarmManager!!.cancel(pendingIntent)
            Toast.makeText(this, "ALARM OFF", Toast.LENGTH_SHORT).show()
        }
    }
}
