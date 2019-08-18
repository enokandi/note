package com.oniktech.fragmenttest


import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var container: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        container = findViewById(R.id.container)
        val fragManager = supportFragmentManager.beginTransaction()
        val fragment = BlankFragment()
        fragManager.add(R.id.container , fragment).commit()
    }
}
