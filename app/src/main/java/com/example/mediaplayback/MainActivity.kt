package com.example.mediaplayback

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var videoPlayer: VideoView
    private val vid_url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"

    private val registerResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                RESULT_OK -> {
                    val videoURI = result.data?.data
                    videoPlayer.setVideoURI(videoURI)
                    videoPlayer.start()
                }
                RESULT_CANCELED -> ""
                RESULT_FIRST_USER -> ""
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        videoPlayer = findViewById(R.id.videoplayer)
        setListeners()

    }

    private fun setListeners() {
        this.findViewById<Button>(R.id.btn_select).setOnClickListener {
            //intent should be provided by viewmodel
            val intent = Intent()
            intent.type = "video/*"
            intent.action = Intent.ACTION_PICK
            registerResult.launch(intent)
        }
        this.findViewById<Button>(R.id.btn_upload).setOnClickListener {
            //send to backend server
        }
        this.findViewById<Button>(R.id.btn_stream).setOnClickListener{
            try {
                val mediacontroller = MediaController(this)
                videoPlayer.setMediaController(mediacontroller)
                videoPlayer.setVideoPath(vid_url)
                videoPlayer.start()
            }catch (e : Exception){
                e
            }
            videoPlayer.setOnErrorListener { mediaPlayer, i, i2 ->
                true
            }
            videoPlayer.setOnCompletionListener {
                println("completed")
            }
        }
    }
}