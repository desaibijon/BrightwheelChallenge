package com.example.brightwheelchallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.brightwheelchallenge.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // show component list fragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ComponentListFragment.newInstance())
                .commitNow()
        }
    }
}