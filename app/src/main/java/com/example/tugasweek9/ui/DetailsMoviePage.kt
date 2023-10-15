package com.example.tugasweek9.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.tugasweek9.R

class DetailsMoviePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_movie_page)

        val backgroundCover = findViewById<ImageView>(R.id.posterDetail)
        val detailImage = findViewById<ImageView>(R.id.imagedetail)
        val DescripMovie = findViewById<TextView>(R.id.textDetailPage)


    }
}