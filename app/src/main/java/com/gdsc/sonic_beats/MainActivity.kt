package com.gdsc.sonic_beats

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageButton
import android.widget.SeekBar
import com.gdsc.sonic_beats.R.id.play_btn

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    lateinit var runnable: Runnable
    private var handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playbtn: ImageButton = findViewById(play_btn)
        val seekBar: SeekBar = findViewById(R.id.seekbar)

        val mediaplayer = MediaPlayer.create(this, R.raw.song1)
        seekBar.progress = 0
        seekBar.max = mediaplayer.duration

        playbtn.setOnClickListener{
            if(!mediaplayer.isPlaying){
                mediaplayer.start()
                playbtn.setImageResource(R.drawable.baseline_pause_24)
            } else{
                mediaplayer.pause()
                playbtn.setImageResource(R.drawable.baseline_pause_24)

            }
        }
        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, pos: Int, changed: Boolean) {
                mediaplayer.seekTo(pos)

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                TODO("Not yet implemented")
            }
        })

        runnable = Runnable {
            seekBar.progress = mediaplayer.currentPosition
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)
        mediaplayer.setOnCompletionListener {
            playbtn.setImageResource(R.drawable.baseline_play_arrow_24)
            seekBar.progress = 0
        }
        }


    }

