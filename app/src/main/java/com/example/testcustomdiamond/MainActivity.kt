package com.example.testcustomdiamond

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mosavi.customdiamond.PlayProCustomView
import com.mosavi.customdiamond.properties.DiamondAnimationProperties

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val playProDiamond = findViewById<PlayProCustomView>(R.id.playProDiamond)
        playProDiamond.setup(DiamondAnimationProperties.Fast,constraintLayout = findViewById(R.id.root_layout))
    }
}