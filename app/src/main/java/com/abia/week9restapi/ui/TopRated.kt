package com.abia.week9restapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abia.week9restapi.R
import com.abia.week9restapi.api.Network
import com.abia.week9restapi.response.MovieResponse
import com.abia.week9restapi.ui.adapter.TopRatedAdapter
import kotlinx.coroutines.launch

class TopRated : AppCompatActivity() {
    private lateinit var adapter: TopRatedAdapter
    private var listMovie = mutableListOf<MovieResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_rated)

        val rvTopRated = findViewById<RecyclerView>(R.id.rvTopRated) // Correct the view type
        rvTopRated.layoutManager = LinearLayoutManager(this)
        adapter = TopRatedAdapter(listMovie)
        rvTopRated.adapter = adapter

        lifecycleScope.launch {
            val result = Network.getService(this@TopRated).getTopRated(
                page = 1
            )
            Log.d("debug","total page -> ${result.totalPages}")
            result.result.map {
                Log.d("debug", "hasilnya-> ${it.title} - ${it.overview}")
                listMovie.add(it)
            }
            // Update the RecyclerViewnya
            adapter.notifyDataSetChanged()
        }
    }
}