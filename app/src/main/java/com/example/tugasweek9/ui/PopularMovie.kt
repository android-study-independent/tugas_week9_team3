package com.example.tugasweek9.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasweek9.R
import com.example.tugasweek9.data.api.Network
import com.example.tugasweek9.data.response.MoviePopularResponse
import com.example.tugasweek9.ui.adapter.PopularAdapter
import kotlinx.coroutines.launch

class PopularMovie : AppCompatActivity() {

    private lateinit var adapter: PopularAdapter
    private var listMoviePopular = mutableListOf<MoviePopularResponse>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popular_movie)

        val detailbutton = findViewById<ImageView>(R.id.btnmoredetails)

//        detailbutton.setOnClickListener {
//            startActivity(Intent(this, DetailsMoviePage::class.java))
//
//        }

//        val btnDetailMov = findViewById<CardView>(R.id.btnCardView)
//
//        btnDetailMov.setOnClickListener {
//            startActivity(Intent(this,DetailsMoviePage::class.java))
//        }


        val rvPopularMovie = findViewById<RecyclerView>(R.id.rvpopular)
        rvPopularMovie.layoutManager = LinearLayoutManager(this)
        adapter = PopularAdapter(listMoviePopular)
        rvPopularMovie.adapter = adapter



        lifecycleScope.launch {
            val result = Network.getService(this@PopularMovie).getPopular(
                lang = "en",
                page = 1
            )

            Log.d("debug", "hasil total pagenya -> ${result.totalPage}")
            result.results.map {
                Log.d("debug", "hasilnya -> ${it.title}-${it.overview}")
                listMoviePopular.add(it)
            }

            adapter.notifyDataSetChanged()
        }


    }

}