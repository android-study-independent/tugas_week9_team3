package com.example.tugasweek9.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.tugasweek9.R
import com.example.tugasweek9.data.api.Network
import com.example.tugasweek9.data.response.MoviePopularResponse
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

//    private lateinit var  adapter: PopularAdapter
    private var listMoviePopular = mutableListOf<MoviePopularResponse>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val rvPopularMovie = findViewById<RecyclerView>(R.id.rvpopularnew)
//        rvPopularMovie.layoutManager = LinearLayoutManager(this)
//        adapter = PopularAdapter(listMoviePopular)
//        rvPopularMovie.adapter=adapter

//        lifecycleScope.launch {
//           val result = Network.getService(this@MainActivity).getPopular(
//                lang = "en",
//                page = 1
//            )
//
//            Log.d("debug", "hasil total pagenya -> ${result.totalPage}")
//            result.results.map {
//                Log.d("debug","hasilnya -> ${it.title}")
//            }
//
////            adapter.notifyDataSetChanged()
//        }

    }
}