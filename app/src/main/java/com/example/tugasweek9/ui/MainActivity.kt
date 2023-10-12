package com.example.tugasweek9.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.tugasweek9.R
import com.example.tugasweek9.data.api.Network
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        lifecycleScope.launch {
            val result = Network.getService().getNowPlaying(
                token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxZTZhMTViZDdjMGNkMDFiM2Y2M2M3Zjc1NGQyMzJhZSIsInN1YiI6IjY1MjRkMjMxYjAwNDBhMDEzYTE0NTU5MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.AztZWX2ZfCYkqUmuXNmvGcny5pOoJJ3xgdIvGhshbb8",
                lang = "en",
                page = 1
            )
            result.results.map {
                Log.d("debug", "hasilnya -> $it")
            }
        }

    }
}