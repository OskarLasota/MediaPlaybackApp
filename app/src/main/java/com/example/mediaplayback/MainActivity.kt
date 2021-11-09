package com.example.mediaplayback

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    companion object {
        val OBTAIN_VIDEO_REQUEST = 4
    }

    private val registerResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        when(result.resultCode){
            RESULT_OK -> ""
            RESULT_CANCELED ->""
            RESULT_FIRST_USER -> ""
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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