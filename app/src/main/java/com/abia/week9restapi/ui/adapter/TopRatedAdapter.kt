package com.abia.week9restapi.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abia.week9restapi.R
import com.abia.week9restapi.response.MovieResponse
import com.squareup.picasso.Picasso

class TopRatedAdapter(private val listMovie: List<MovieResponse>) :
    RecyclerView.Adapter<TopRatedAdapter.TopRatedHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_top_rated, parent, false)
        return TopRatedHolder(view)
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    override fun onBindViewHolder(holder: TopRatedHolder, position: Int) {
        holder.bindView(listMovie[position])
    }

    inner class TopRatedHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bindView(movie: MovieResponse) {
            //inisiasi view
            val imgPoster = view.findViewById<ImageView>(R.id.imgPoster)
            val tvTitle = view.findViewById<TextView>(R.id.tvTittle)
            val tvRating =view.findViewById<TextView>(R.id.tvRating)
            val tvOverview =view.findViewById<TextView>(R.id.tvOverview)

            tvTitle.text = movie.tittle
            tvRating.text = "${movie.voteAverage}"
            tvOverview.text = movie.overview.toString()


            val path = buildPosterPath(movie.posterPath)
            // load image from url into imageview
            Picasso.get().load(path).into(imgPoster)
        }

        private fun buildPosterPath(posterPath: String?): String {
            return "https://image.tmdb.org/t/p/w500/$posterPath"
        }
    }

    }