package com.example.tugasweek9.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import com.example.tugasweek9.R
import com.example.tugasweek9.data.api.Network
import com.example.tugasweek9.data.response.UpcomingMovieResponse
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var btnNowPlaying: Button
    lateinit var btnTopRated: Button
    lateinit var btnPopular: Button
    lateinit var btnUpcoming: Button
    private var upcomingMovie = mutableListOf<UpcomingMovieResponse>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnNowPlaying = findViewById(R.id.btnNowPlaying)
        btnPopular = findViewById(R.id.btnPopular)
        btnTopRated = findViewById(R.id.btnTopRated)
        btnUpcoming = findViewById(R.id.btnUpcoming)
        lifecycleScope.launch {
           val result = Network.getService(this@MainActivity).getPopular(
                lang = "en",
                page = 1
            )

            Log.d("debug", "hasil total pagenya -> ${result.totalPage}")
            result.results.map {
                Log.d("debug","hasilnya -> ${it.overview}")
            }
        }

        btnUpcoming.setOnClickListener {
            val intent = Intent(this, UpcomingMovie::class.java)
            startActivity(intent)
            finish()
        }

    }
}