package com.example.counttimerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    var START_MILLI_SECONDS = 60000L
    lateinit var countDownTimer: CountDownTimer
    lateinit var editInputTxt: EditText
    lateinit var timerTxt: TextView
    lateinit var timerBtn: Button
    lateinit var resetBtn: Button
    var isTimerRunning: Boolean = false
    var time_in_milli_seconds = 0L



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timerBtn = findViewById(R.id.timerBtn)
        resetBtn = findViewById(R.id.resetBtn)
        timerTxt = findViewById(R.id.timerTxt)
        timerBtn.setOnClickListener {
            if (isTimerRunning) {
                pauseTimer()
            } else {
                editInputTxt = findViewById(R.id.editInputTxt)
                val inputTime = editInputTxt.text.toString()
                time_in_milli_seconds = inputTime.toLong() * START_MILLI_SECONDS
                startTimer(time_in_milli_seconds)
            }
        }

        resetBtn.setOnClickListener {
            resetTimer()
        }

    }


    private fun resetTimer() {
        time_in_milli_seconds = START_MILLI_SECONDS
        updateTextUI()
        resetBtn.visibility = View.INVISIBLE
    }

    private fun startTimer(timeInMilliSeconds: Long) {
        countDownTimer = object: CountDownTimer(timeInMilliSeconds, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                time_in_milli_seconds = millisUntilFinished
                updateTextUI()
            }

            override fun onFinish() {
                timerTxt.text = ""

            }
        }
        countDownTimer.start()

        isTimerRunning = true
        timerBtn.text = "Pause"
        resetBtn.visibility = View.INVISIBLE
    }

    private fun updateTextUI() {
        val minute = (time_in_milli_seconds / 1000) / 60
        val second = (time_in_milli_seconds / 1000) % 60
        timerTxt.text = "$minute:$second"
    }

    private fun pauseTimer() {
        timerBtn.text = "Start"
        countDownTimer.cancel()
        isTimerRunning = false
        resetBtn.visibility = View.VISIBLE
    }
}