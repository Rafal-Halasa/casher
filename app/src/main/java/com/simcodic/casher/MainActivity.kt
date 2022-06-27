package com.simcodic.casher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.simcodic.casher.logic.navigation.setNavigation

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        setNavigation()
    }

}