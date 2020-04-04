package com.buidanhtuan.saveme.view.fragment

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

import com.buidanhtuan.saveme.R
import com.buidanhtuan.saveme.model.Note
import com.buidanhtuan.saveme.view_model.database.DatabaseHelper
import kotlinx.android.synthetic.main.fragment_speed.*
import java.util.*

class SpeedFragment : Fragment() {
    private var mVoiceInputTv: TextView? = null
    private var mSpeakBtn: Button? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_speed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    private fun init(){
        mVoiceInputTv = voiceInput
        mSpeakBtn = btnSpeak
        mSpeakBtn!!.setOnClickListener { startVoiceInput() }
    }
    private fun startVoiceInput() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?")
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT)
        } catch (a: ActivityNotFoundException) {
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQ_CODE_SPEECH_INPUT -> {
                if (resultCode == Activity.RESULT_OK && null != data) {
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    mVoiceInputTv!!.text = result[0]
                    val note = Note(0,"text","speed",result[0],"","")
                    DatabaseHelper.insertData(note)
                }
            }
        }
    }

    companion object {
        private const val REQ_CODE_SPEECH_INPUT = 100
    }
}
