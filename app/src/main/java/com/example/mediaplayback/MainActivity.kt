package com.example.mediaplayback

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.VideoView
import androidx.activity.result.contract.ActivityResultContracts
import java.io.File
import java.net.URI

class MainActivity : AppCompatActivity() {

    private lateinit var videoPlayer : VideoView

    private val registerResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        when(result.resultCode){
            RESULT_OK -> {
                val videoURI = result.data?.data
                videoPlayer.setVideoURI(videoURI)
                videoPlayer.start()
            }
            RESULT_CANCELED ->""
            RESULT_FIRST_USER -> ""
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        videoPlayer = findViewById(R.id.videoplayer)
        setListeners()

    }

    private fun setListeners(){
        this.findViewById<Button>(R.id.btn_upload).setOnClickListener {
            //intent should be provided by viewmodel
            val intent = Intent()
            intent.type = "video/*"
            intent.action = Intent.ACTION_PICK
            registerResult.launch(intent)
        }
    }
}