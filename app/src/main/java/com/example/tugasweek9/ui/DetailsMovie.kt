package com.example.tugasweek9.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.tugasweek9.R
import com.example.tugasweek9.data.response.MoviePopularResponse
import com.example.tugasweek9.data.response.MovieResponse
import com.example.tugasweek9.data.response.MovieTopRatedResponse
import com.example.tugasweek9.data.response.UpcomingMovieResponse
import com.squareup.picasso.Picasso

class DetailsMovie : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_movie)

        val getDataUpcomingMovie = intent.getParcelableExtra<UpcomingMovieResponse>("Upcoming")
        val getDataNowPlayingMovie = intent.getParcelableExtra<MovieResponse>("Now Playing")
        val getTopRatedMovie = intent.getParcelableExtra<MovieTopRatedResponse>("Top Rated")
        val getPopularMovie = intent.getParcelableExtra<MoviePopularResponse>("Popular")
        if (getDataUpcomingMovie != null) {

            val txtTitle : TextView = findViewById(R.id.txtTitleDetails)
            val txtDetailSinopsis:TextView = findViewById(R.id.txtDetailsSinopsis)
            val releaseDate : TextView = findViewById(R.id.txtDateInDetails)
            val rating: TextView = findViewById(R.id.txtRatingInDetails)
            val imgBackdrop : ImageView = findViewById(R.id.imgBackdrop)
            val imgPoster : ImageView = findViewById(R.id.imgPoster)
            val popularity : TextView = findViewById(R.id.numbersPopularity)

            txtTitle.text = getDataUpcomingMovie.title
            txtDetailSinopsis.text = getDataUpcomingMovie.overview
            releaseDate.text = getDataUpcomingMovie.releaseDate
            rating.text = getDataUpcomingMovie.voteAverage.toString()
            popularity.text = getDataUpcomingMovie.popularity.toString()

            val pathBackdrop = buildPosterUpcoming(getDataUpcomingMovie.backdropPath)
            val path = buildPosterUpcoming(getDataUpcomingMovie.posterPath)
            Picasso.get().load(pathBackdrop).into(imgBackdrop)
            Picasso.get().load(path).into(imgPoster)

        } else if (getDataNowPlayingMovie != null){
            val txtTitle : TextView = findViewById(R.id.txtTitleDetails)
            val txtDetailSinopsis:TextView = findViewById(R.id.txtDetailsSinopsis)
            val releaseDate : TextView = findViewById(R.id.txtDateInDetails)
            val rating: TextView = findViewById(R.id.txtRatingInDetails)
            val imgBackdrop : ImageView = findViewById(R.id.imgBackdrop)
            val imgPoster : ImageView = findViewById(R.id.imgPoster)
            val popularity : TextView = findViewById(R.id.numbersPopularity)

            txtTitle.text = getDataNowPlayingMovie.title
            txtDetailSinopsis.text = getDataNowPlayingMovie.overview
            releaseDate.text = getDataNowPlayingMovie.releaseDate
            rating.text = getDataNowPlayingMovie.voteAverage.toString()
            popularity.text = getDataNowPlayingMovie.popularity.toString()

            val pathBackdrop = buildPosterUpcoming(getDataNowPlayingMovie.backdropPath)
            val path = buildPosterUpcoming(getDataNowPlayingMovie.posterPath)
            Picasso.get().load(pathBackdrop).into(imgBackdrop)
            Picasso.get().load(path).into(imgPoster)
        } else if (getTopRatedMovie != null) {
            val txtTitle : TextView = findViewById(R.id.txtTitleDetails)
            val txtDetailSinopsis:TextView = findViewById(R.id.txtDetailsSinopsis)
            val releaseDate : TextView = findViewById(R.id.txtDateInDetails)
            val rating: TextView = findViewById(R.id.txtRatingInDetails)
            val imgBackdrop : ImageView = findViewById(R.id.imgBackdrop)
            val imgPoster : ImageView = findViewById(R.id.imgPoster)
            val popularity : TextView = findViewById(R.id.numbersPopularity)

            txtTitle.text = getTopRatedMovie.title
            txtDetailSinopsis.text = getTopRatedMovie.overview
            releaseDate.text = getTopRatedMovie.releaseDate
            rating.text = getTopRatedMovie.voteAverage.toString()
            popularity.text = getTopRatedMovie.popularity.toString()

            val pathBackdrop = buildPosterUpcoming(getTopRatedMovie.backdropPath)
            val path = buildPosterUpcoming(getTopRatedMovie.posterPath)
            Picasso.get().load(pathBackdrop).into(imgBackdrop)
            Picasso.get().load(path).into(imgPoster)
        } else if (getPopularMovie != null) {
            val txtTitle : TextView = findViewById(R.id.txtTitleDetails)
            val txtDetailSinopsis:TextView = findViewById(R.id.txtDetailsSinopsis)
            val releaseDate : TextView = findViewById(R.id.txtDateInDetails)
            val rating: TextView = findViewById(R.id.txtRatingInDetails)
            val imgBackdrop : ImageView = findViewById(R.id.imgBackdrop)
            val imgPoster : ImageView = findViewById(R.id.imgPoster)
            val popularity : TextView = findViewById(R.id.numbersPopularity)

            txtTitle.text = getPopularMovie.title
            txtDetailSinopsis.text = getPopularMovie.overview
            releaseDate.text = getPopularMovie.releaseDate
            rating.text = getPopularMovie.voteAverage.toString()
            popularity.text = getPopularMovie.popularity.toString()

            val pathBackdrop = buildPosterUpcoming(getPopularMovie.backdropPath)
            val path = buildPosterUpcoming(getPopularMovie.posterPath)
            Picasso.get().load(pathBackdrop).into(imgBackdrop)
            Picasso.get().load(path).into(imgPoster)
        }

    }

    private fun buildPosterUpcoming(posterPath: String?): String {
        return "https://image.tmdb.org/t/p/w500/$posterPath"
    }
}