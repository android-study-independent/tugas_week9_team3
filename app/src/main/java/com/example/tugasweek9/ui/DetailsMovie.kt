package com.example.tugasweek9.ui

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.tugasweek9.R
import com.example.tugasweek9.data.response.MoviePopularResponse
import com.example.tugasweek9.data.response.MovieResponse
import com.example.tugasweek9.data.response.MovieTopRatedResponse
import com.example.tugasweek9.data.response.UpcomingMovieResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import retrofit2.http.Tag

class DetailsMovie : AppCompatActivity() {

    private var movieNowPlaying = ""
    private var movieUpcoming = ""
    private var movieTopRated = ""
    private var moviePopular = ""
    private lateinit var firebaseAuth: FirebaseAuth

    private var isMyFav = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_movie)

        val btnFavorite = findViewById<Button>(R.id.btnFavorite)
        val getDataUpcomingMovie = intent.getParcelableExtra<UpcomingMovieResponse>("Upcoming")
        val getDataNowPlayingMovie = intent.getParcelableExtra<MovieResponse>("Now Playing")
        val getTopRatedMovie = intent.getParcelableExtra<MovieTopRatedResponse>("Top Rated")
        val getPopularMovie = intent.getParcelableExtra<MoviePopularResponse>("Popular")

        firebaseAuth = FirebaseAuth.getInstance()

        if (firebaseAuth.currentUser != null) {
            checkFavorite()
        }


        movieNowPlaying = intent.getParcelableExtra<MovieResponse>("Now Playing").toString()
        movieUpcoming = intent.getParcelableExtra<UpcomingMovieResponse>("Upcoming").toString()
        moviePopular = intent.getParcelableExtra<MoviePopularResponse>("Popular").toString()
        movieTopRated = intent.getParcelableExtra<MovieTopRatedResponse>("Top Rated").toString()

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
        }
        else if (getDataNowPlayingMovie != null){
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
        }
        else if (getTopRatedMovie != null) {
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
        }
        else if (getPopularMovie != null) {
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

        btnFavorite.setOnClickListener {

            if (firebaseAuth.currentUser == null) {
                Toast.makeText(this, "Not Log In", Toast.LENGTH_SHORT).show()

            } else {
                if (isMyFav){
                    removeFavorite()
                } else {
                    addFavorite()
                }
            }

        }


    }

    private fun buildPosterUpcoming(posterPath: String?): String {
        return "https://image.tmdb.org/t/p/w500/$posterPath"
    }

    private fun checkFavorite() {
        Log.d(TAG, "Check Favorite")

        val ref = FirebaseDatabase.getInstance().getReference("Users")

        ref.child(firebaseAuth.uid!!).child("Favorite").child(movieNowPlaying)
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    isMyFav = snapshot.exists()
                    if (isMyFav) {
                        Log.d(TAG, "Available on favorite")
                        val btnFavorite = findViewById<Button>(R.id.btnFavorite)

                        btnFavorite.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.baseline_favorite_24, 0, 0)
                        btnFavorite.text = "Remove Favorite"
                    } else {
                        Log.d(TAG, "Remove on favorite")
                        val btnFavorite = findViewById<Button>(R.id.btnFavorite)

                        btnFavorite.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.baseline_favorite_border_24, 0, 0)
                        btnFavorite.text = "Add Favorite"
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
    }

    private fun addFavorite () {
        Log.d(TAG, "Add to favirite")
        val timestamp = System.currentTimeMillis()

        val hashMap = HashMap<String, Any>()
//        hashMap["NowPlaying"] = movieNowPlaying
//        hashMap["TopRated"] = movieTopRated
        hashMap["Upcoming"] = movieUpcoming
//        hashMap["Popular"] = moviePopular
        hashMap["Timestamp"] = timestamp

        val refDb = FirebaseDatabase.getInstance().getReference("Users")
//        val ref = FirebaseFirestore.getInstance().getNamedQuery("Favorite")
        refDb.child(firebaseAuth.uid!!).child("Favorite").child(movieNowPlaying)
            .setValue(hashMap)
            .addOnSuccessListener {
                Log.d(TAG, "Add too favorite")
                Toast.makeText(this, "Succes add movie to favorite", Toast.LENGTH_SHORT).show()

            }
            .addOnFailureListener {e ->
                Log.d(TAG, "ADD to fav failed ${e.message}")
                Toast.makeText(this, "Failed add movie to favorite ${e.message}", Toast.LENGTH_SHORT).show()
            }


    }

    private fun removeFavorite() {
        Log.d(TAG, "Remove from favvorite")

        val ref = FirebaseDatabase.getInstance().getReference("Users")
        ref.child(firebaseAuth.uid!!).child("Favorite").child(movieNowPlaying)
            .removeValue()
            .addOnSuccessListener {
                Log.d(TAG, "Remove from favorite")
                Toast.makeText(this, "Succes remove movie to favorite", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {e ->
                Log.d(TAG, "Remove from fav failed ${e.message}")
                Toast.makeText(this, "Failed remove movie to favorite ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }


}