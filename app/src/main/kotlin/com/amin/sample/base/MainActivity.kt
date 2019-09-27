package com.amin.sample.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.amin.sample.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        when (Configs.sampleMode) {
            Configs.SampleTypes.BLOG_POSTS -> findNavController(R.id.navHostFragment).setGraph(R.navigation.nav_main)
            Configs.SampleTypes.SHUTTER_STOCK -> findNavController(R.id.navHostFragment).setGraph(R.navigation.nav_shutter_stock)
            else -> findNavController(R.id.navHostFragment).setGraph(R.navigation.nav_main)
        }
    }
}
