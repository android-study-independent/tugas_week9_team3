package com.example.tugasweek9.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.tugasweek9.R
import com.example.tugasweek9.data.api.Networking
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
           val result = Networking.getService(this@MainActivity).getPopular(
                lang = "en",
                page = 1
            )

            Log.d("debug", "hasil total pagenya -> ${result.totalPage}")
            result.results.map {
                Log.d("debug","hasilnya -> ${it.overview}")
            }
        }

    }
}