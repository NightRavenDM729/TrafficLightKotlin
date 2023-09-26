package com.example.traffic_light_kotlin

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import java.util.Timer
import java.util.TimerTask

class MainActivity : Activity() {

    var imTrafficLight: ImageView? = null
    var counter: Int = 0
    var timerLight: Timer? = null
    var isLaunch: Boolean = false
    var imageArray: IntArray =
        intArrayOf(R.drawable.semafor_red, R.drawable.semafor_yellow, R.drawable.semafor_green)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imTrafficLight = findViewById(R.id.imTrafficLight)
    }

    fun onClickStartStop(view: View) {
        view as ImageView

        isLaunch = if (!isLaunch) {
            startStop()
            view.setImageResource(R.drawable.button_stop)
            true
        } else {
            view.setImageResource(R.drawable.button_start)
            imTrafficLight?.setImageResource(R.drawable.semafor_grey)
            timerLight?.cancel()
            counter = 0
            false
        }

    }

    fun startStop() {
        timerLight = Timer()
        timerLight?.schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    imTrafficLight?.setImageResource(imageArray[counter])
                    counter++
                    if (counter == 3) {
                        counter = 0
                    }
                }
            }
        }, 0, 1000)

    }
}